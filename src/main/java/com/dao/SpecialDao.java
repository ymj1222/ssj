package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Special;
import com.util.Page;
import com.util.Pageh;

@Repository
public class SpecialDao {
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 添加--------
	 * 
	 * @param orders
	 */
	@Transactional
public 	void add(Special special) {
		entityManager.persist(special);
	}
	
	/**
	 * 根据code删除
	 * 
	 * @param code
	 */
	@Transactional
	public void delete(String code) {
		String jpql="select a from Special as a where a.code=:code";
		Query query=entityManager.createQuery(jpql);
		Special s=(Special) query.setParameter("code", code).getSingleResult();
		entityManager.remove(s);
	}
	/**
	 * 根据id得到orders的集合
	 * 
	 * @param id
	 * @return
	 */
		public List<Special> select(Pageh page){
			if (null != page.getObject1()&& ""!=page.getObject1()) {
				String jpql = "select a from Special as a  order by a.id desc ";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("sname", page.getObject1());
				return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).setParameter(1, page.getObject1()).getResultList();
			} else {
				String jpql = "select a from Special as a  order by a.id desc ";
				Query query = entityManager.createQuery(jpql);
				return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();

			}
		}
	
	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
public	Integer gettotal(String sname) {
	if (null != sname) {
		String jpql = "select count(a) from Special as a where a.name like '%'||:sname||'%' ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("sname", sname);
		return  Integer.valueOf( query.getResultList().get(0).toString());
	} else {
		String jpql = "select count(a) from Special as a ";
		Query query = entityManager.createQuery(jpql);
		return  Integer.valueOf( query.getResultList().get(0).toString());
	}
}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Special updatequery(String code) {
		String jpql = "select o from Special as o where o.code=:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", code);
		return (Special) query.getSingleResult();
	}

	/**
	 * 修改数据-------------
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(Special special) {
		String jpql="update zj_special set name =:name,type=:type where code=:code";
		Query query=entityManager.createNativeQuery(jpql);
		query.setParameter("name", special.getName());
		query.setParameter("type", special.getType());
		query.setParameter("code", special.getCode());
		query.executeUpdate();
	}
}
