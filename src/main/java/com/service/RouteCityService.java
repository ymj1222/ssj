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
		RouteCity r=dao.findByWayBillNo(code);
		dao.deleteById(r.getId());
	} 
	public void addRouteCity(RouteCity routeCity) {
		dao.save(routeCity);
	}
	public RouteCity queryByRouteCode(String wayBillNo,String cityCode) {
		return dao.findByWayBillNoAndCityCode(wayBillNo,cityCode);
	}
	public RouteCity findBySequence(String seqyence,String wayBillNo) {
		int s=Integer.parseInt(seqyence);
		return dao.findBySequenceAndWayBillNo(s,wayBillNo);
	}
	public int queryNumber (String wayBillNo) {
		return dao.countByWayBillNo(wayBillNo);
	}
	
}
