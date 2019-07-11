package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthDao;
import com.entity.Auth;
import com.entity.Pageh;
@Service
public class AuthService {
	@Autowired
AuthDao dao;
	public List<Auth> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public void delete(String code,String isDelete) {
		dao.delete(code,isDelete);
	} 
	public void add(Auth auth) {
		dao.add(auth);
	}
	public List<Auth> query(){
		return dao.query();
	}
	public Auth queryByCode(String code) {
		return dao.queryByCode(code);
	}
	public void update(Auth auth) {
		dao.update(auth);
	}
	public Integer gettotal(Pageh ph) {
		int pageCount = 0;
		int rowCount = dao.gettotal(ph);
		if ((rowCount % ph.getPageSize()) == 0) {
			pageCount = rowCount / ph.getPageSize();
		} else {
			pageCount = rowCount / ph.getPageSize() + 1;
		}
		return pageCount;
	}
	public List<List<Auth>> queryPcode(){
		List<List<Auth>> list = new ArrayList<>();
		List<Auth> listp = dao.queryPcode();
		for (Auth auth : listp) {
			List<Auth> listchild = dao.queryChild(auth.getCode());
			listchild.add(0,auth);
			list.add(listchild);
		}
		return list;
	}
}
