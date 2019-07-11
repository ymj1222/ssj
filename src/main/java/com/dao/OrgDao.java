package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Org;
import com.entity.Pageh;

@Repository
public class OrgDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Org o) {
		entityManager.persist(o);
	}
	public Org queryByCode(String code) {
		String jpql="select z from Org as z where z.code=?1";
		Query query=entityManager.createQuery(jpql);
		return (Org) query.setParameter(1, code).getResultList().get(0);
	}
	@Transactional
	public void update(Org org) {
		String jpql="  update Org set name=?1,managerCode=?2,pCode=?3 where code=?4";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, org.getName()).setParameter(2, org.getManagerCode()).setParameter(3, org.getpCode()).setParameter(4, org.getCode()).executeUpdate();
	}
	@Transactional
	public void delete(String code) {
		String jpql=" delete from Org where code =?1";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, code).executeUpdate();
	}
	public List<Org> select(Pageh ph){
		if(""!=ph.getObject1()) {
			String jpql="select a from Org as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Org as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}
	}
	public List<Org> query(){
		String jpql="select z from Org as z";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Org as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Org as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
	@Transactional
	public void enable(String state,String code) {
		String jpql=" update Org set state=?1 where code=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, state).setParameter(2, code).executeUpdate();
	}
}
