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
		Addr a=dao.findByWayBillNo(Integer.parseInt(code));
		dao.deleteById(a.getId());
	} 
	public void addAddr(Addr addr) {
		dao.save(addr);
	}
	public Addr queryByCode(String code) {
		return dao.findByCityCode(code);
	}
	public int queryNumber(String wayBillNo) {
		WayBill ww=w.findByWayBillNo(wayBillNo);
		return dao.countByWayBillNo(ww.getId().toString());
	}
	public Addr findCityCodeByWayBillNo(String wayBillNo) {
		WayBill ww=w.findByWayBillNo(wayBillNo);
		return dao.findByWayBillNoOrderByIdDesc(ww.getId().toString()).get(0);
	}
}
