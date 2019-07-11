package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountAuth;

@Repository
public class AccountAuthDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
public void add(AccountAuth aa) {
	entityManager.persist(aa);
}
@SuppressWarnings("unchecked")
public List<AccountAuth>queryByAccount(String account){
	String jpql="select aa from AccountAuth as aa where aa.account=?1 ";
			  Query query =  entityManager.createQuery(jpql);
	return  query.setParameter(1, account).getResultList();
}
@Transactional
public void delete(String account,String authCode) {
	  String jpql = "DELETE FROM AccountAuth WHERE account = ?1 and authCode=?2";
	    Query query = entityManager.createQuery(jpql);
	    query.setParameter(1, account).setParameter(2, authCode);
	    query.executeUpdate();
}
}
