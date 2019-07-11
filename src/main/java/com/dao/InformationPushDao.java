package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.InformationPush;
import com.util.Pageh;

@Repository
public class InformationPushDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 * 
	 * @param agent
	 */
	@Transactional
	public void add(InformationPush informationPush) {
		entityManager.persist(informationPush);
	};

	/**
	 * 带分页的模糊搜索
	 * 
	 * @param pageh
	 * @return
	 */
	public List<InformationPush> select(Pageh pageh) {
		String jpql = "select t from InformationPush t";
		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
			jpql += " where t.phone like '%'||?||'%/' ";
		}
		jpql += " order by t.id desc";
		Query query = entityManager.createQuery(jpql);
		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
			query.setParameter(1, pageh.getPhone());
		}
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<InformationPush> list = query.getResultList();
		return list;
	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public String gettotal(Pageh pageh) {
		String jpql = "select count(t) from InformationPush t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.phone like '%'||?||'%/'";
		}
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		Long count = (Long) query.getResultList().get(0);
		return count.toString();
	};

}
