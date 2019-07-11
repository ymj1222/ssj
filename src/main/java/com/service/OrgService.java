package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrgDao;
import com.entity.Org;
import com.entity.Pageh;
@Service
public class OrgService {
	@Autowired
OrgDao dao;
	public List<Org> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public List<Org> query(){
		return dao.query();
	}
	public void delete(String code) {
		dao.delete(code);
	} 
	public void add(Org account) {
		dao.add(account);
	}
	public Org queryByCode(String code) {
		return dao.queryByCode(code);
	}
	public void enable(String state,String code) {
		dao.enable(state,code);
	}
	public void update(Org org) {
		dao.update(org);
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

}
