package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Account;
import com.entity.Pageh;
import com.entity.Verification;
@Repository
public class AccountDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
public void add(Account account) {
	entityManager.persist(account);
}
public Account queryByAccount(String account) {
	String jpql="select a from Account as a where a.account=?1";
	  Query query =  entityManager.createQuery(jpql);
	  List list= query.setParameter(1, account).getResultList();
		if(list.size()>0) {
			return (Account) list.get(0);
		}else {
			return null;
		}
}
public Verification login(String account,String password) {
	String jpql="select a from Account as a where a.account=?1 and a.password=?2";
	  Query query =  entityManager.createQuery(jpql);
		return  (Verification) query.setParameter(1, account).setParameter(2, password).getResultList().get(0);
}
@Transactional
public void delete (String state,String code) {
	String jpql="UPDATE Account set state=?1 where code=?2 ";
	Query query =  entityManager.createQuery(jpql);
	query.setParameter(1, state);
	query.setParameter(2, code);
	query.executeUpdate();
}
public List<Account> select(Pageh ph){
	if(""!=ph.getObject1()) {
		
		String jpql="select a from Account as a where a.name like '%'||?1||'%' order by a.id desc ";
		Query query =  entityManager.createQuery(jpql);
		return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
	}else {
		String jpql="select a from Account as a  order by a.id desc ";
		Query query =  entityManager.createQuery(jpql);
		return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
		
	}
	}
public Integer gettotal(Pageh ph) {
	if(null!=ph.getObject1()) {
		String jpql="select count(z) from Account as z where z.name like '%'||?1||'%'";
		Query query =  entityManager.createQuery(jpql);
		Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
		return l.intValue();
	}else {
		String jpql="select count(z) from Account as z";
		Query query =  entityManager.createQuery(jpql);
		Long l=(Long) query.getResultList().get(0);
		return  l.intValue();
	}
}
@Transactional
public void update(Account account) {
	String jpql="update Account set password=?1 where account=?2";
	Query query=entityManager.createQuery(jpql);
	query.setParameter(1, account.getPassword()).setParameter(2, account.getAccount()).executeUpdate();
}
public Account daoGou(String code) {
	String jpql="select a from Account as a where a.code=?1";
	Query query=entityManager.createQuery(jpql);
	List list=query.setParameter(1, code).getResultList();
	if(list.size()>0) {
		return (Account) list.get(0);
	}else {
		return null;
	}
}
}
