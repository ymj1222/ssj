package com.dao;

import java.util.List;
/**
 * ����ԱDao
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.ShoppingGuide;
import com.util.Pageh;

@Repository
public class ShoppingGuideDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * ���
	 * 
	 * @param terminal
	 */
	@Transactional
	public void add(ShoppingGuide shoppingGuide) {
		shoppingGuide.setType(1001);
		entityManager.persist(shoppingGuide);
	};

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		ShoppingGuide shoppingGuide = entityManager.find(ShoppingGuide.class, id);
		entityManager.remove(shoppingGuide);
	};

	/**
	 * 
	 * @param pageh
	 * @return
	 */
	public List<ShoppingGuide> select(Pageh pageh) {
		String jpql = "select t from ShoppingGuide t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like concat( '%',?1,'%') ";
		}
		jpql += " order by t.id desc";
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<ShoppingGuide> list = query.getResultList();
		return list;
	};

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public String gettotal(Pageh pageh) {

		String jpql = "select count(t) from ShoppingGuide t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like concat( '%',?1,'%')";
		}
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		return query.getResultList().get(0).toString();
	};

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public ShoppingGuide updatequery(int id) {
		ShoppingGuide shoppingGuide = entityManager.find(ShoppingGuide.class, id);
		return shoppingGuide;
	};

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(ShoppingGuide shoppingGuide) {
		String jpql = "update zj_shopping_guide set name =?,phone=?,hobby=?,sex=?,updator=?,updateTime=? where id=?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, shoppingGuide.getName());
		query.setParameter(2, shoppingGuide.getPhone());
		query.setParameter(3, shoppingGuide.getHobby());
		query.setParameter(4, shoppingGuide.getSet());
		query.setParameter(5, shoppingGuide.getUpdator());
		query.setParameter(6, shoppingGuide.getUpdateTime());
		query.setParameter(7, shoppingGuide.getId());
		entityManager.merge(shoppingGuide);
	};

	/**
	 * �ͷ���ץȥ�����ʱ�򣬽�״̬�ĳ�æ��
	 * 
	 * @param id
	 */
	@Transactional
	public void updatetypebusy(int code) {
		String jpql = "update zj_shopping_guide  set type = 1003 where id = ?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, code);
		query.executeUpdate();
	};

	/**
	 * ���ͷ����߻��߷�����֮�󣬽�״̬�ĳ�����
	 * 
	 * @param id
	 */
	@Transactional
	public void updatetypeon(int code) {
		String jpql = "update zj_shopping_guide  set type = 1001 where id = ?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, code);
		query.executeUpdate();
	};

	/**
	 * �ͷ����ߣ���״̬��Ϊ����
	 * 
	 * @param id
	 */
	@Transactional
	public void updatetypeoff(int code) {
		String jpql = "update zj_shopping_guide  set type = 1002 where id = ?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, code);
		query.executeUpdate();
	};

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public List<ShoppingGuide> queryAll() {
		String sql = "from ShoppingGuide";
		Query query = entityManager.createQuery(sql);
		List<ShoppingGuide> list = query.getResultList();
		return list;
	};

}
