package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Article;
import com.entity.Special;
import com.util.Pageh;

@Repository
public class ArticleDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加----------
	 * 
	 * @param orders
	 */
	@Transactional
	public void add(Article article) {
		entityManager.persist(article);
	}

	/**
	 * 根据code删除=========
	 * 
	 * @param code
	 */
	@Transactional
	public void delete(String code) {
		String jpql = "select z from Article as z where z.code=:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", code);
		Article aa = (Article) query.getResultList().get(0);
		entityManager.remove(aa);
	}

	/**
	 * 根据id得到orders的集合
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> select(Pageh page) {
		if (null != page.getObject1() && "" != page.getObject1()) {
			String jpql = "select a from Article as a where a.name like '%'||:sname||'%' order by id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", page.getObject1());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow())
					.setParameter("sname", page.getObject1()).getResultList();
		} else {
			String jpql = "select a from Article  as a order by id desc ";
			Query query = entityManager.createQuery(jpql);
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();

		}
	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public Integer gettotal(String object1) {
		if (null != object1 && "" != object1) {
			String jpql = "select count(a) from Article as a  where a.name like '%'||:sname||'%'";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", object1);
			return Integer.valueOf(query.getResultList().get(0).toString());
		} else {
			String jpql = "select count(a) from Article as a";
			Query query = entityManager.createQuery(jpql);
			return Integer.valueOf(query.getResultList().get(0).toString());
		}
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Article updatequery(String code) {
		String jpql = "select a from Article as a where a.code =:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", code);
		List list =query.getResultList();
		if (list.size()>0) {
		return (Article) list.get(0);
		}
		else {
			return null;
		}
	}

	public Article selectIs(int is) {
		String jpql = "select a from Article as a where a.issue=:issue";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("issue", is);
		return (Article) query.getSingleResult();
	}

	public Article articleReport(String code) {
		String jpql = "select a from Article  as a where a.code=:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", code);
		List list =query.getResultList();
		if (list.size()>0) {
		return (Article) list.get(0);
		}
		else {
			return null;
		}
	}

	/**
	 * 修改数据---------
	 * 
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(Article article) {
		String jpql = "update zj_article set name=:name,content=:content where code=:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("name", article.getName());
		query.setParameter("content", article.getContent());
		query.setParameter("code", article.getCode());
		query.executeUpdate();

	}

	@Transactional
	public void updateIs(int article, String code) {
		String jpql = "update zj_article set issue=:issue where code=:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("issue", article);
		query.setParameter("code", code);
		query.executeUpdate();

	}

	public List<Special> selectName() {
		String jpql = "select a from Special  as a ";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
