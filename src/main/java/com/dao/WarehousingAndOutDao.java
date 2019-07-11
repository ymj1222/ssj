package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.WarehousingAndOut;

@Repository
public class WarehousingAndOutDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 * 
	 * @param 入庫方法
	 */
	
	public void insert(WarehousingAndOut WAO) {
		entityManager.persist(WAO);
	}

	/**
	 * 查出庫方法
	 * 
	 * @return
	 */
	public List<WarehousingAndOut> select(String date, Integer type, int pageNow, int pageSize) {
		String sql = "select w from WarehousingAndOut w where w.type= :type ";
		if (date != null && date != "") {
			sql += " and w.date= :date";
		}
		sql += " order by date desc";
		Query query = entityManager.createQuery(sql);
		query.setParameter("type", type);
		if (date != null && date != "") {
			query.setParameter("date", date);
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<WarehousingAndOut> result = query.getResultList();
		return result;
	}

	public Long gettotal(String date, Integer type) {
		String sql = "select count(w) from WarehousingAndOut w where w.type= :type ";
		if (date != null && date != "") {
			sql += " and w.date= :date";
		}
		Query query = entityManager.createQuery(sql);
		query.setParameter("type", type);
		if (date != null && date != "") {
			query.setParameter("date", date.toString());
		}
		return (Long) query.getSingleResult();
	}

	public WarehousingAndOut selectorder(Long code) {
		Query query = entityManager.createQuery("select w from WarehousingAndOut w where w.ordersCode= :order");
		query.setParameter("order", code);
		return (WarehousingAndOut) query.getSingleResult();
	}

	public void cancel(Long code) {
		Query query = entityManager.createNativeQuery("delete from WarehousingAndOut where ordersCode= :order");
		query.setParameter("order", code);
		query.executeUpdate();
	}
}
