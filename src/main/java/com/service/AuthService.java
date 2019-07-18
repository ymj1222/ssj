package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.AuthDao;
import com.entity.Auth;
import com.entity.Pageh;
@Service
public class AuthService {
	@Autowired
AuthDao dao;
	public Page<Auth> queryAll(String name, Pageable pageable){
		Page<Auth> page = null;
		if(null !=name) {
			page=dao.findByNameContaining(name, pageable);
		}else{
			page=dao.findAll(pageable);
		}
		return page;
	}
	public void delete(String code,String isDelete) {
		dao.delete(code,isDelete);
	} 
	public void add(Auth auth) {
		dao.save(auth);
	}
	public List<Auth> query(){
		return dao.findAll();
	}
	public Auth queryByCode(String code) {
		return dao.findByCodeAndIsDelete(code,"1");
	}
	public void update(Auth auth) {
		dao.update(auth.getName(),auth.getUrl(),auth.getIsBase(),auth.getMenuType(),auth.getDescri(),auth.getCode());
	}

	public List<List<Auth>> queryPcode(){
		List<List<Auth>> list = new ArrayList<>();
		List<Auth> listp = dao.queryPcode("");
		for (Auth auth : listp) {
			List<Auth> listchild = dao.findByPCode(auth.getCode());
			listchild.add(0,auth);
			list.add(listchild);
		}
		return list;
	}
}
