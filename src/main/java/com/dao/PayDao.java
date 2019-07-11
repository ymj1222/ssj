package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pay;

@Repository
public class PayDao {
	@PersistenceContext
	private EntityManager entityManager;
	public List<Pay>select(){
		String jpql=" select p from Pay as p order by p.code asc";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}
	@Transactional
	public void update(String code) {
		String jpql="update Pay set code=?1 where code=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, code).setParameter(2, "1").executeUpdate();
	}
	@Transactional
	public void update1(String code,String name) {
		String jpql="update Pay set code=?1 where code=?2 and name=?3";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, "1").setParameter(2, code).setParameter(3, name).executeUpdate();
	}
}
