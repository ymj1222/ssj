package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Auth;
import com.entity.Pageh;

@Repository
public class AuthDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Auth auth) {
		entityManager.persist(auth);
	}
	public Auth queryByCode(String code) {
		String jpql="select z from Auth as z where z.code=?1 and z.isDelete=?2";
		  Query query =  entityManager.createQuery(jpql);
		  List list=query.setParameter(1,code).setParameter(2,"1").getResultList();
		  if(list.size()>0) {
			  
			  return  (Auth) list.get(0);
		  }else {
			  return null;
		  }
	}
	@Transactional
	public void delete(String code,String isDelete) {
		String jpql="update  Auth set isDelete=?1 where code = ?2";
		Query query =  entityManager.createQuery(jpql);
		query.setParameter(1, isDelete).setParameter(2, code).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<Auth>queryPcode(){
        String jpql=" select z from Auth as z where z.pCode is null or z.pCode=?1";
        Query query =  entityManager.createQuery(jpql);
        return query.setParameter(1, "").getResultList();
        }
	public List<Auth>queryChild(String pCode){
		String jpql="select z from Auth as z where z.pCode=?1";
		Query query =  entityManager.createQuery(jpql);
		return query.setParameter(1, pCode).getResultList();
	}
	public List<Auth>select(Pageh ph){
		if(""!=ph.getObject1()) {
			String jpql="select a from Auth as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Auth as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}
	}
	public List<Auth>query(){
		String jpql=" select z from Auth as z";
		Query query =  entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Auth as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Auth as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
	@Transactional
	public void update(Auth auth) {
		String jpql=" update Auth set name=?1,url=?2,isBase=?3,menuType=?4,descri=?5 where code=?6";
		Query query =  entityManager.createQuery(jpql);
		query.setParameter(1, auth.getName()).setParameter(2, auth.getUrl()).setParameter(3, auth.getIsBase()).setParameter(4, auth.getMenuType()).setParameter(5,auth.getDescri()).setParameter(6, auth.getCode()).executeUpdate();
	}
}
