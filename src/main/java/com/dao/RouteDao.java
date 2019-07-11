package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Route;

@Repository
public class RouteDao {
	@PersistenceContext
	private EntityManager entityManager;
	public  List<Route> findAll(){
		String jpql="select r from Route as r";
		Query query =entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public  Route findByCode(String  code) {
		String jpql="select r from Route as r where r.code = ?1";
		Query query=entityManager.createQuery(jpql);
		return (Route) query.setParameter(1,code).getResultList().get(0);
	}
	@Transactional
	public  void deleteByCode(String  code) {
		String jpql=" delete from Route where code = ?1";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, code).executeUpdate();
	}
	
}
