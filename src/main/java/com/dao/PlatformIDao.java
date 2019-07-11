package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.PlatformI;

@Repository
public class PlatformIDao {
	@PersistenceContext
	private EntityManager entityManager;
	public PlatformI query() {
		String jpql="select p from PlatformI as p";
		Query query=entityManager.createQuery(jpql);
		return (PlatformI) query.getResultList().get(0);
	}
	@Transactional
	public void update(PlatformI p) {
		String jpql=" update PlatformI set logo=?1,companyWebsite=?2,tel=?3,qr=?4 where code=?5";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, p.getLogo()).setParameter(2, p.getCompanyWebsite()).setParameter(3,p.getTel()).setParameter(4, p.getQr()).setParameter(5, p.getCode()).executeUpdate();
	}
}
