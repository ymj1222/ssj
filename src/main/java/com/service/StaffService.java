package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.StaffDao;
import com.entity.Pageh;
import com.entity.Staff;
@Service
public class StaffService {
	@Autowired
StaffDao dao;
	public Page<Staff> queryAll(String name, Pageable pageable){
		Page<Staff> page = null;
		if(null !=name) {
			page=dao.findByNameContaining(name, pageable);
		}else{
			page=dao.findAll(pageable);
		}
		return page;
	}
	public void delete(String code) {
		Staff s=dao.findByCode(code);
		dao.deleteById(s.getId());
	} 
	public List<Staff>query(){
		return dao.findAll();
	}
	public void add(Staff code) {
		dao.save(code);
	}
	public Staff queryByCode(String code) {
		return dao.findByCode(code);
	}
	public void enable(String state,String code) {
		dao.enable(state,code);
	}
	public void update(Staff org) {
		dao.update(org.getName(),org.getOrgCode(),org.getState(),org.getTel(),org.getBirthday(),org.getCode());
	}

}
