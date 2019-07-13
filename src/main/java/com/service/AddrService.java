package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AddrDao;
import com.dao.WayBillDao;
import com.entity.Addr;
import com.entity.WayBill;
@Service
public class AddrService {
	@Autowired
AddrDao dao;
	@Autowired
	WayBillDao w;
	public List<Addr> queryAll(){
		return dao.findAll();
	}
	public void deleteByCode(String code) {
		dao.deleteByCode(code);
	} 
	public void addAddr(Addr addr) {
		dao.addAddr(addr);
	}
	public Addr queryByCode(String code) {
		return dao.findByCode(code);
	}
	public int queryNumber(String wayBillNo) {
		WayBill ww=w.findByWayBillNo(wayBillNo);
		return dao.queryNumber(Integer.toString(ww.getId()));
	}
	public Addr findCityCodeByWayBillNo(String wayBillNo) {
		WayBill ww=w.findByWayBillNo(wayBillNo);
		return dao.findCityCodeBywayBillNo(Integer.toString(ww.getId()));
	}
}
