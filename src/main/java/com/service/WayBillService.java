package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.WayBillDao;
import com.entity.WayBill;
@Service
public class WayBillService {
	@Autowired
WayBillDao dao;
	public List<WayBill> queryAll(){
		return dao.findAll();
	}
	public void deleteByWayBillNo(String wayBillNo) {
		dao.deleteByWayBillNo(wayBillNo);
	} 
	public void addWayBill(WayBill wayBill) {
		dao.addWayBill(wayBill);
	}
	public WayBill queryByWayBillNo(String wayBillNo) {
		return dao.findByWayBillNo(wayBillNo);
	}
	public void updateWayBill(WayBill wayBill) {
		dao.updateWayBill(wayBill);
	}
	
}
