package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pageh;
import com.entity.Role;

@Repository
public class RoleDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Role role) {
		entityManager.persist(role);
	}
	public Role queryByCode(String code) {
		String jpql="select z from Role as z where code=?1";
		Query query=entityManager.createQuery(jpql);
		return (Role) query.setParameter(1, code).getResultList().get(0);
	}
	@Transactional
	public void delete(String state,String code) {
		String jpql="update  Role set state=?1 where code =?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, state).setParameter(2, code).executeUpdate();
	}
	@Transactional
	public void update(Role role) {
		String jpql="update  Role set name=?1 where code =?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, role.getName()).setParameter(2, role.getCode()).executeUpdate();
	}
	public List<Role> select(Pageh ph){
		if(""!=ph.getObject1()) {
			String jpql="select a from Role as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Role as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Role as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Role as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
}
