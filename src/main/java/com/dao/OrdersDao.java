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
		String jpql = "update zj_orders set isconfirmreceipt=:is,receivingTime=:re where code=:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("is", o);
		query.setParameter("re", i);
		query.setParameter("code", code);
		query.executeUpdate();
	}
/**
 * 到货时间 ---------------------------
 * @param deliveryTime
 * @param logisticsNumber
 */
	@Transactional
	public void updateDeliveryTime(String deliveryTime, String logisticsNumber) {
		String jpql = "update zj_orders set deliveryTime=:de where logisticsNumber=:l";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("de", deliveryTime);
		query.setParameter("l", logisticsNumber);
		query.executeUpdate();
	}

	/**
	 * 发货 ---------------------
	 * 
	 * @param orders
	 */
	@Transactional
	public void ordersOut(String code, String is, String logisticsNumber) {
		String jpql = "update zj_orders set isOutOfStock=:is ,logisticsNumber=:l where code =:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("is", is);
		query.setParameter("l", logisticsNumber);
		query.setParameter("code", code);
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
		String jpql = "update zj_orders set orderState=:o where code =:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("o", o);
		query.setParameter("code", code);
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
			String jpql = "select a from Orders as a where a.consignee like '%'||:sname||'%' and a.usersCode =:ss and a.isOutOfStock=:is order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", page.getObject1());
			query.setParameter("ss", page.getObject2());
			query.setParameter("is", page.getObject3());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}else if(null != page.getObject1() && ""!= page.getObject1()) {
			String jpql = "select a from Orders as a where a.consignee like '%'||:sname||'%' and a.usersCode =:ss order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", page.getObject1());
			query.setParameter("ss", page.getObject2());
			 return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}else if(null != page.getObject3()&& page.getObject3()!="") {
			String jpql = "select a from Orders as a where a.isOutOfStock =:is and a.usersCode =:ss order by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("is", page.getObject3());
			query.setParameter("ss", page.getObject2());
			
			 return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();
		}
		else {
			String jpql = "select a from Orders as a where a.usersCode=:ss order  by a.id desc ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("ss", page.getObject2());
			return query.setMaxResults(page.getPageSize()).setFirstResult(page.getPageNow()).getResultList();

		}
	}

	public List<Orders> getOrders(String usersCode) {
		String jpql = "select z from Orders as z where z.users_code=:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", usersCode);
		return query.getResultList();
	}

	/**
	 * 得到订单未发货数据条数
	 * 
	 * @return
	 */
	public List<Orders> orderOutSelect(int pageNow, int pageSize, String is) {
		String jpql = "select o from Orders as o where is_out_of_stock=:is ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("is", is);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).getResultList();

	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public int gettotal( String object1,String usersCode,String isOutOfStock) {
		if (null != object1 && ""!=object1&&isOutOfStock!=null&&isOutOfStock!="") {
			String jpql = "select count(o) from Orders as o  where o.consignee like '%'||:sname||'%' and o.isOutOfStock =:sn and o.usersCode =:ss";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", object1);
			query.setParameter("ss", usersCode);
			query.setParameter("sn", isOutOfStock);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}else if(null != object1 && ""!=object1) {
			String jpql = "select count(o) from Orders as o  where o.consignee like '%'||:sname||'%'  and o.usersCode =:ss";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("sname", object1);
			query.setParameter("ss", usersCode);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}else if(isOutOfStock!=null&&isOutOfStock!="") {
			String jpql = "select count(o) from Orders as o  where   o.isOutOfStock =:sn and o.usersCode =:ss";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("ss", usersCode);
			query.setParameter("sn", isOutOfStock);
			return Integer.valueOf( query.getResultList().get(0).toString());
		}
		
		else {
			String jpql = "select count(o) from Orders as o where o.usersCode=:ss ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("ss", usersCode);
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
		String jpql = "select o from Orders as o where o.code=:code";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("code", code);
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
		String jpql = "update zj_orders set amount=:amount,receivingAddress=:radd,phone=:phone,consignee=:consignee where code=:code";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter("amount", orders.getAmount());
		query.setParameter("radd", orders.getReceivingAddress());
		query.setParameter("phone", orders.getPhone());
		query.setParameter("consignee", orders.getConsignee());
		query.setParameter("code", orders.getCode());
		query.executeUpdate();
	}
}
