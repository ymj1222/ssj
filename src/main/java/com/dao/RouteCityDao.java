package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.RouteCity;

@Repository
public class RouteCityDao {
	@PersistenceContext
	private EntityManager entityManager;
	public  RouteCity findByRouteCode(String  wayBillNo,String cityCode) {
		String jpql="select r from RouteCity as r where r.wayBillNo = ?1 and r.cityCode=?2";
		Query query=entityManager.createQuery(jpql);
		return (RouteCity) query.setParameter(1, wayBillNo).setParameter(2,cityCode).getResultList().get(0);
	}
	public int queryNumber(String wayBillNo) {
		String jpql="select count(r) from RouteCity as r where r.wayBillNo=?1";
		Query query=entityManager.createQuery(jpql);
		Long l=(Long)query.setParameter(1, wayBillNo).getResultList().get(0);
		return l.intValue();
	}
	@Transactional
	public void deleteByRouteCode(String  code) {
		String jpql="delete from RouteCity where wayBillNo =?1";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1,code).executeUpdate();
	}
	@Transactional
	public void addRouteCity(RouteCity route) {
		entityManager.persist(route);
	}
	public RouteCity findBySequence(String sequence,String wayBillNo) {
		String jpql="select r from RouteCity as r where r.sequence=?1 and r.wayBillNo=?2";
		Query query=entityManager.createQuery(jpql);
		System.out.println(sequence+"----"+wayBillNo);
		Integer i=Integer.parseInt(sequence);
		return (RouteCity) query.setParameter(1,i).setParameter(2, wayBillNo).getResultList().get(0);
	}
}
