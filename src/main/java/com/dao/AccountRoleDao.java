package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountRole;

@Repository
public class AccountRoleDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public 	void add(AccountRole accountRole) {
		entityManager.persist(accountRole);
	}
	public List<AccountRole> queryByAccount(String account){
		String jpql=" select z from zj_account_role as z where z.account=?1";
		Query query=entityManager.createQuery(jpql);
		return query.setParameter(1, account).getResultList();
	}
	@Transactional
	public void delete(String account,String roleCode) {
		String jpql="delete from  zj_account_role where account=?1 and roleCode=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1,account).setParameter(2, roleCode).executeUpdate();
	}
	public List<AccountRole> select(int pageNow,int pageSize){
		String jpql="select z from zj_account_role as z ";
		Query query=entityManager.createQuery(jpql);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).getResultList();
	}
	public Integer gettotal() {
		String jpql="select count(z) from zj_account_role as z";
		Query query=entityManager.createQuery(jpql);
		Long l=(Long) query.getResultList().get(0);
		return l.intValue();
	}
	@Transactional
	public void update(AccountRole accountRole) {
		String jpql=" update zj_account_role set roleCode=?1 where account=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, accountRole.getRoleCode()).setParameter(2, accountRole.getAccount()).executeUpdate();
	}
}
