package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CityDao;
import com.entity.City;
@Service
public class CityService {
	@Autowired
CityDao dao;
	public List<City> queryAll(){
		return dao.findAll();
	}
	public City queryByCode(String code) {
		return dao.findByCode(Integer.parseInt(code));
	}
	
}
