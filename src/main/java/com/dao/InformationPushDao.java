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
	 * @param informationPush
	 */
	@Transactional
	public void add(InformationPush informationPush) {
		entityManager.persist(informationPush);
	};

	/**
	 *得到所有数据
	 * @param pageh
	 * @return
	 */
	public List<InformationPush> select(Pageh pageh) {
		String jpql = "select t from InformationPush t";
		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
			jpql += " where t.phone like '%'||?1||'%' ";
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
	 * 得到所有数据条数
	 * @param pageh
	 * @return
	 */
	public String gettotal(Pageh pageh) {
		String jpql = "select count(t) from InformationPush t";
		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
			jpql += " where t.phone like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(jpql);
		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
			query.setParameter(1, pageh.getPhone());
		}
		Long count = (Long) query.getResultList().get(0);
		return count.toString();
	};

}
