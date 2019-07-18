package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PayDao;
import com.entity.Pay;
@Service
public class PayService {
	
	@Autowired
    PayDao dao;
	
	public List<Pay> queryAll(){
	    return dao.findOrderByIdDesc();
	}
	
	public void update(String code) {
		dao.update(code,"1");
	}
	public void update1(String code,String name) {
		dao.update1("1",code,name);
	}

}
