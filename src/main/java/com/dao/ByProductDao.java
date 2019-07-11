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
	 * 添加
	 * 
	 * @param 每個商品的分數
	 */
	public void insert(ByProduct byProduct) {
		entityManager.persist(byProduct);
	}

}
