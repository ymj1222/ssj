package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Logistics;
import com.entity.Pageh;

@Repository
public class LogisticsDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Logistics l) {
		entityManager.persist(l);
	}
	@Transactional
	public void delete(String code) {
		String jpql="delete from Logistics  where code=?1";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, code).executeUpdate();
	}
	public List<Logistics> select(Pageh ph	){
		if(""!=ph.getObject1()) {
			String jpql="select a from Logistics  as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Logistics  as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Logistics as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Logistics as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
}
