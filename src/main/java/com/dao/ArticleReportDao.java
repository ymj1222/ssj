package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.ArticleReport;
import com.util.Pageh;

@Repository
public class ArticleReportDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Ìí¼Ó
	 * 
	 * @param orders
	 */
	@Transactional
	public void add(ArticleReport article) {
		entityManager.persist(article);
	}

	public List<ArticleReport> select(Pageh page) {
		if (null != page.getObject1()&& ""!=page.getObject1()) {
			String jpql = "select a from ArticleReport as a where a.articleName like '%'||:sname||'%' order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", page.getObject1());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow())
					.setParameter("sname", page.getObject1()).getResultList();
		} else {
			String jpql = "select a from ArticleReport as a  order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();

		}
	}

	public Integer gettotal(String object1) {
		if (null != object1&& ""!=object1) {
			String jpql = "select count(a) from ArticleReport as a where a.articleName like '%'||:sname||'%' ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", object1);
			return Integer.valueOf(query.getResultList().get(0).toString());
		} else {
			String jpql = "select count(a) from ArticleReport as a";
			Query query = entityManager.createQuery(jpql);

			return Integer.valueOf(query.getResultList().get(0).toString());
		}
	}
}
