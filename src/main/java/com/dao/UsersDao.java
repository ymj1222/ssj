package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pageh;
import com.entity.Users;

@Repository
public class UsersDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Users user) {
		entityManager.persist(user);
	}
	public Users queryByCode(String code) {
		String jpql="select u from Users as u where u.code=?1";
		Query query=entityManager.createQuery(jpql);
		List list=query.setParameter(1, code).getResultList();
		if(list.size()>0) {
			return (Users) list.get(0);
		}else {
			return null;
		}
	}
	public String queryByAccount(String account) {
		String jpql="  select u.code from Users as u where u.account=?1";
		Query query=entityManager.createQuery(jpql);
		List list=query.setParameter(1, account).getResultList();
		if(list.size()>0) {
			return (String) list.get(0);
		}else {
			return null;
		}
	}
	@Transactional
	public void update(Users user) {
		String jpql="update Users set name=?1,phone=?2,wechat=?3,sex=?4,city=?5,age=?6,levelMark=?7,goldCoin=?8,integral=?9 where code=?10";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, user.getName()).setParameter(2, user.getPhone()).setParameter(3, user.getWechat()).setParameter(4, user.getSex()).setParameter(5, user.getCity()).setParameter(6, user.getAge()).setParameter(7,user.getLevelMark()).setParameter(8,user.getGoldCoin()).setParameter(9, user.getIntegral()).setParameter(10, user.getCode()).executeUpdate();
	}
	@Transactional
	public void delete(String state,String code) {
		String jpql="update Users set state=?1 where code=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, state);
		query.setParameter(2,code);
		query.executeUpdate();
	}
	public List<Users> select(Pageh ph){
		if(""!=ph.getObject1()) {
			String jpql="select a from Users as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Users as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}		
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Users as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Users as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
}
