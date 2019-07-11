package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.BrowsingHistory;

@Repository
public class BrowsingHistoryDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(BrowsingHistory bh) {
		entityManager.persist(bh);
	}
	@Transactional
	public void delete(String code) {
		String jpql="delete from BrowsingHistory where code=?1";
		Query query =  entityManager.createQuery(jpql);
		query.setParameter(1, code).executeUpdate();
	}
	public List<BrowsingHistory> select (int pageNow,int pageSize,String usersCode){
		String jpql=" select z from BrowsingHistory as z where z.usersCode=?1 ";
		Query query =  entityManager.createQuery(jpql);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).setParameter(1,usersCode).getResultList();
	}
	public Integer gettotal(String userCode) {
		String jpql="select count(z) from BrowsingHistory as z where z.usersCode=?1";
		Query query =  entityManager.createQuery(jpql);
		Long l=(Long)query.setParameter(1, userCode).getResultList().get(0);
		return l.intValue();
	}
}