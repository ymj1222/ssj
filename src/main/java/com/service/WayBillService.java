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
		WayBill w=dao.findByWayBillNo(wayBillNo);
		dao.deleteById(w.getId());
	} 
	public void addWayBill(WayBill wayBill) {
		dao.save(wayBill);
	}
	public WayBill queryByWayBillNo(String wayBillNo) {
		return dao.findByWayBillNo(wayBillNo);
	}
	public void updateWayBill(WayBill wayBill) {
		dao.update(wayBill.getNumber(),wayBill.getUnit(),wayBill.getStartCity(),wayBill.getEndCity(),wayBill.getrName(),wayBill.getrPhone(),wayBill.getrAddr(),wayBill.getWayBillNo());
	}
	
}
