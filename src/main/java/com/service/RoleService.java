package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleDao;
import com.entity.Pageh;
import com.entity.Role;
@Service
public class RoleService {
	@Autowired
RoleDao dao;
	public List<Role> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public void delete(String state,String code) {
		dao.delete(state,code);
	} 
	public void update(Role role) {
		dao.update(role);
	} 
	public void add(Role role) {
		dao.add(role);
	}
	public Role queryByCode(String code) {
		return dao.queryByCode(code);
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
