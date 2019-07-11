package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RouteCityDao;
import com.entity.RouteCity;
@Service
public class RouteCityService {
	@Autowired
RouteCityDao dao;
	public void deleteByRouteCode(String code) {
		dao.deleteByRouteCode(code);
	} 
	public void addRouteCity(RouteCity routeCity) {
		dao.addRouteCity(routeCity);
	}
	public RouteCity queryByRouteCode(String wayBillNo,String cityCode) {
		return dao.findByRouteCode(wayBillNo,cityCode);
	}
	public RouteCity findBySequence(String seqyence,String wayBillNo) {
		return dao.findBySequence(seqyence,wayBillNo);
	}
	public int queryNumber (String wayBillNo) {
		return dao.queryNumber(wayBillNo);
	}
	
}
