package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.City;

@Repository
public class CityDao {
	@PersistenceContext
	private EntityManager entityManager;
	public List<City>findAll(){
		String jpql="select c from City as c";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public City findByCode(String code) {
		String jpql=" select c from City as c where c.code =?1";
		Query query=entityManager.createQuery(jpql);
		Integer i=Integer.parseInt(code);
		List list=query.setParameter(1, i).getResultList();
		if(list.size()>0) {
			return (City) list.get(0);
		}else {
			return null;
		}
	}
}
