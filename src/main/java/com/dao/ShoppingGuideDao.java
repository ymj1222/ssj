package com.dao;

import java.util.List;
/**
 * 导购员Dao
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
	 * 添加
	 * 
	 * @param terminal
	 */
	@Transactional
	public void add(ShoppingGuide shoppingGuide) {
		shoppingGuide.setType(1001);
		entityManager.persist(shoppingGuide);
	};

	/**
	 * 根据id删除
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
	 * 得到数据条数
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
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public ShoppingGuide updatequery(int id) {
		ShoppingGuide shoppingGuide = entityManager.find(ShoppingGuide.class, id);
		return shoppingGuide;
	};

	/**
	 * 修改数据
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
	 * 客服被抓去服务的时候，将状态改成忙线
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
	 * 当客服上线或者服务完之后，将状态改成在线
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
	 * 客服下线，将状态改为下线
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
	 * 得到所有数据
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
