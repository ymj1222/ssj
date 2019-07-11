package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.entity.ByProduct;

@Repository
public class ByProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * ���
	 * 
	 * @param ÿ����Ʒ�ķ֔�
	 */
	public void insert(ByProduct byProduct) {
		entityManager.persist(byProduct);
	}

}
