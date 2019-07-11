package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.ProductEvaluation;

@Repository
public class ProductEvaluationDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Ìí¼Ó
	 * 
	 * @param Ôu·Ö
	 */
	public void insert(ProductEvaluation ProductEvaluation) {
		entityManager.persist(ProductEvaluation);
	}

	public List<ProductEvaluation> select(Long code, int pageNow, int pageSize) {
		String sql = "select e from ProductEvaluation e where 1=1";
		if (code != null && code != 0) {
			sql += " and productCode= :code";
		}
		Query query = entityManager.createQuery(sql);
		if (code != null && code != 0) {
			query.setParameter("code", code);
		}
		List<ProductEvaluation> result = query.getResultList();
		return result;
	}

	public Long page(Long code) {
		String sql = "select count(e) from ProductEvaluation e where 1=1";
		if (code != null && code != 0) {
			sql += " and productCode= :code";
		}
		Query query = entityManager.createQuery(sql);
		if (code != null && code != 0) {
			query.setParameter("code", code);
		}
		return (Long)query.getSingleResult();
	}
}
