package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.WayBill;

@Repository
public interface WayBillDao  extends JpaRepository<WayBill, Integer> {
	WayBill findByWayBillNo(String wayBillNo);
	@org.springframework.data.jpa.repository.Query("select w from WayBill as w where w.id = ?1")
	WayBill f(Integer id);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update WayBill set number=?1,unit=?2,startCity=?3,endCity=?4,rName=?5,rPhone=?6,rAddr=?7 where wayBillNo=?8")
	void update(int number,String unit,String startCity,String endCity,String rName,String rPhone,String rAddr,String wayBillNo);
}