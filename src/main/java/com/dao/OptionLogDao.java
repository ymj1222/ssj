package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.OptionLog;

@Repository
public class OptionLogDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(OptionLog ol) {
		entityManager.persist(ol);
	}
	public List<OptionLog> select(int pageNow,int pageSize){
		String jpql="select o from OptionLog as o order by o.id desc";
		Query query=entityManager.createQuery(jpql);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).getResultList();
	}
	public Integer gettotal() {
		String jpql="select count(z) from OptionLog as z";
		Query query =  entityManager.createQuery(jpql);
		Long l=(Long)query.getResultList().get(0);
		return l.intValue();
	} 
}
