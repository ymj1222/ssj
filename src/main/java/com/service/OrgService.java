package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.OrgDao;
import com.entity.Org;
import com.entity.Pageh;
@Service
public class OrgService {
	@Autowired
OrgDao dao;
	public Page<Org> queryAll(String name, Pageable pageable){
		Page<Org> page = null;
		if(null !=name) {
			page=dao.findByNameContaining(name, pageable);
		}else{
			page=dao.findAll(pageable);
		}
		return page;
	}
	public List<Org> query(){
		return dao.findAll();
	}
	public void delete(String code) {
		Org o=dao.findByCode(code);
		dao.deleteById(o.getId());
	}
	public void add(Org account) {
		dao.save(account);
	}
	public Org queryByCode(String code) {
		return dao.findByCode(code);
	}
	public void enable(String state,String code) {
		dao.enable(state,code);
	}
	public void update(Org org) {
		dao.update(org.getName(),org.getManagerCode(),org.getpCode(),org.getCode());
	}

}
