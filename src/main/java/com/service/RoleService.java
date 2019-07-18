package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.RoleDao;
import com.entity.Pageh;
import com.entity.Role;
@Service
public class RoleService {
	@Autowired
RoleDao dao;
	public Page<Role> queryAll(String name, Pageable pageable){
		Page<Role> page = null;
		if(null !=name) {
			page=dao.findByNameContaining(name, pageable);
		}else{
			page=dao.findAll(pageable);
		}
		return page;
	}
	public void delete(String state,String code) {
		dao.delete(state,code);
	} 
	public void update(Role role) {
		dao.update(role.getName(),role.getCode())
		;
	} 
	public void add(Role role) {
		dao.save(role);
	}
	public Role queryByCode(String code) {
		return dao.findByCode(code);
	}
}
