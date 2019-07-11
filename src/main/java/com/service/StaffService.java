package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StaffDao;
import com.entity.Pageh;
import com.entity.Staff;
@Service
public class StaffService {
	@Autowired
StaffDao dao;
	public List<Staff> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public void delete(String code) {
		dao.delete(code);
	} 
	public List<Staff>query(){
		return dao.query();
	}
	public void add(Staff code) {
		dao.add(code);
	}
	public Staff queryByCode(String code) {
		return dao.queryByCode(code);
	}
	public void enable(String state,String code) {
		dao.enable(state,code);
	}
	public void update(Staff org) {
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
