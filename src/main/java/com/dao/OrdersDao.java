package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Orders;
import com.util.Pageh;

@Repository
public class OrdersDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加--------------------
	 * 
	 * @param orders
	 */
	@Transactional
	public void add(Orders order) {
		entityManager.persist(order);
	}

	/**
	 * 收货 ---------------------
	 * 
	 * @param orders
	 */
	@Transactional
	public void updateIs(String code, int o, String i) {
		String jpql = "update zj_orders set isconfirmreceipt=?1,receiving_time=?2 where code=?3";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, o);
		query.setParameter(2, i);
		query.setParameter(3, code);
		query.executeUpdate();
	}
/**
 * 到货时间 ---------------------------
 * @param deliveryTime
 * @param logisticsNumber
 */
	@Transactional
	public void updateDeliveryTime(String deliveryTime, String logisticsNumber) {
		String jpql = "update zj_orders set delivery_time=?1 where logistics_number=?2";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, deliveryTime);
		query.setParameter(2, logisticsNumber);
		query.executeUpdate();
	}

	/**
	 * 发货 ---------------------
	 * 
	 * @param orders
	 */
	@Transactional
	public void ordersOut(String code, String is, String logisticsNumber) {
		String jpql = "update zj_orders set is_out_of_stock=?1,logistics_number=?2 where code =?3";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, is);
		query.setParameter(2, logisticsNumber);
		query.setParameter(3, code);
		query.executeUpdate();

	}

	/**
	 * 订单取消 ---------------------------
	 * 
	 * @param code
	 * @param o
	 */
	@Transactional
	public void ordersCanel(String code, int o) {
		String jpql = "update zj_orders set order_state=?1 where code =?2";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, o);
		query.setParameter(2, code);
		query.executeUpdate();
	}

	/**
	 * 根据id得到orders的集合
	 * 
	 * @param id
	 * @return
	 */
	public List<Orders> select( Pageh page) {
		if (null != page.getObject1()&& ""!=page.getObject1()&&page.getObject3()!=null&&page.getObject3()!="") {
			String jpql = "select a from Orders as a where a.consignee like '%'||?1||'%' and a.usersCode =?2 and a.isOutOfStock=?3 order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, page.getObject1());
			query.setParameter(2, page.getObject2());
			query.setParameter(3, page.getObject3());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}else if(null != page.getObject1() && ""!= page.getObject1()) {
			String jpql = "select a from Orders as a where a.consignee like '%'||?1||'%' and a.usersCode =?2 order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, page.getObject1());
			query.setParameter(2, page.getObject2());
			 return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}else if(null != page.getObject3()&& page.getObject3()!="") {
			String jpql = "select a from Orders as a where a.isOutOfStock =?1 and a.usersCode =?2 order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, page.getObject3());
			query.setParameter(2, page.getObject2());
			
			 return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}
		else {
			String jpql = "select a from Orders as a where a.usersCode=?1 order  by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, page.getObject2());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();

		}
	}

	public List<Orders> getOrders(String usersCode) {
		String jpql = "select z from Orders as z where z.usersCode=?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, usersCode);
		return query.getResultList();
	}

	/**
	 * 得到订单未发货数据条数
	 * 
	 * @return
	 */
	public List<Orders> orderOutSelect(int pageNow, int pageSize, String is) {
		String jpql = "select o from Orders as o where o.isOutOfStock=?1 ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, is);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).getResultList();

	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public int gettotal( String object1,String usersCode,String isOutOfStock) {
		if (null != object1 && ""!=object1&&isOutOfStock!=null&&isOutOfStock!="") {
			String jpql = "select count(o) from Orders as o  where o.consignee like '%'||?1||'%' and o.isOutOfStock =?3 and o.usersCode =?2";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, object1);
			query.setParameter(2, usersCode);
			query.setParameter(3, isOutOfStock);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}else if(null != object1 && ""!=object1) {
			String jpql = "select count(o) from Orders as o  where o.consignee like '%'||?1||'%'  and o.usersCode =?2";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, object1);
			query.setParameter(2, usersCode);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}else if(isOutOfStock!=null&&isOutOfStock!="") {
			String jpql = "select count(o) from Orders as o  where   o.isOutOfStock =?2 and o.usersCode =?1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, usersCode);
			query.setParameter(2, isOutOfStock);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}
		
		else {
			String jpql = "select count(o) from Orders as o where o.usersCode=?1 ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter(1, usersCode);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Orders updatequery(String code) {
		String jpql = "select o from Orders as o where o.code=?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, code);
		return (Orders) query.getSingleResult();
	}

	/**
	 * 修改数据 ---------------------
	 * 
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(Orders orders) {
		String jpql = "update zj_Orders set amount=?1,receiving_address=?2,phone=?3,consignee=?4 where code=?5";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, orders.getAmount());
		query.setParameter(2, orders.getReceivingAddress());
		query.setParameter(3, orders.getPhone());
		query.setParameter(4, orders.getConsignee());
		query.setParameter(5, orders.getCode());
		query.executeUpdate();
	}
}
