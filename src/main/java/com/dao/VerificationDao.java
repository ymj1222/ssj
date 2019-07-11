package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.Account;
import com.entity.Verification;

@Repository
public class VerificationDao {
	@PersistenceContext
	private EntityManager entityManager;
	public Verification queryByCode(String account,String password) {
		String jpql="select a from Account as a where a.account=?1 and a.password=?2 and a.state=?3";
		Query query=entityManager.createQuery(jpql);
		List list= query.setParameter(1, account).setParameter(2, password).setParameter(3, "0").getResultList();
		if(list.size()>0) {
			Account ac=(Account) list.get(0);
			Verification v=new Verification();
			v.setAccount(ac.getAccount());
			v.setPassword(ac.getPassword());
			return v;
		}else {
			return null;
		}
	}
}
