package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RouteDao;
import com.entity.Route;
@Service
public class RouteService {
	@Autowired
RouteDao dao;
	public List<Route> queryAll(){
		return dao.findAll();
	}
	public void deleteByCode(String code) {
		dao.deleteByCode(code);
	} 
	public Route queryByCode(String code) {
		return dao.findByCode(code);
	}
	
}
