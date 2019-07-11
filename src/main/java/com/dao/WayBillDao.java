package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.WayBill;

@Repository
public class WayBillDao {
	@PersistenceContext
	private EntityManager entityManager;
	public List<WayBill> findAll(){
		String jpql="select w from WayBill as w";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public WayBill findByWayBillNo(String  wayBillNo) {
		String jpql="select w from WayBill as w where w.wayBillNo = ?1";
		Query query=entityManager.createQuery(jpql);
		List list=query.setParameter(1, wayBillNo).getResultList();
		if(list.size()>0) {
			
			return (WayBill) list.get(0);
		}else {
			return null;
		}
	}
	public WayBill findById(String  id) {
		String jpql="select w from WayBill as w where w.id = ?1";
		Query query=entityManager.createQuery(jpql);
		Integer i=Integer.parseInt(id);
		List list=query.setParameter(1, i).getResultList();
		if(list.size()>0) {
			
			return (WayBill) list.get(0);
		}else {
			return null;
		}
	}
	@Transactional
	public void deleteByWayBillNo(String  wayBillNo) {
		String jpql = "SELECT w FROM WayBill as w WHERE wayBillNo=?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, wayBillNo);
		WayBill w = (WayBill) query.getResultList().get(0);
		entityManager.remove(w);
	}
	@Transactional
	public void addWayBill(WayBill wayBill){
		entityManager.persist(wayBill);
	}
	@Transactional
	public void updateWayBill(WayBill w) {
		String jpql="update WayBill set number=?1,unit=?2,startCity=?3,endCity=?4,rName=?5,rPhone=?6,rAddr=?7 where wayBillNo=?8";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, w.getNumber()).setParameter(2, w.getUnit()).setParameter(3, w.getStartCity()).setParameter(4, w.getEndCity()).setParameter(5, w.getrName()).setParameter(6, w.getrPhone()).setParameter(7,w.getrAddr()).setParameter(8,w.getWayBillNo()).executeUpdate();
		
	}
}