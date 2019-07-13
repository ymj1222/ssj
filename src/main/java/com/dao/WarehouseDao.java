package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.Warehouse;
@Repository
public class WarehouseDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 *
	 * @param 入庫方法
	 */
	public void insert(Warehouse warehouse) {
		entityManager.persist(warehouse);
	}

	public Warehouse addamount(int id,int amount) {
		Warehouse warehouse = entityManager.find(Warehouse.class, id);
		int real=amount+warehouse.getAmount();
		warehouse.setAmount(real);
		return warehouse;
	}

	public Integer amount(Long code) {
		Query query = entityManager.createQuery("select w.amount from Warehouse w where w.productCode= ?1");
		query.setParameter(1, code);
		return (Integer) query.getSingleResult();
	}

	public void stockOut(Long code, Long amount) {
		Query query = entityManager.createNativeQuery("update Warehouse set amount= ?1 where product_Code= ?2");
		query.setParameter(1, amount);
		query.setParameter(2, code);
		query.executeUpdate();
	}

	public List<Warehouse> selectwarehouse(String name, int pageNow, int pageSize) {
		String sql = "select w from Warehouse  w where w.productCode in(select p.code from Product p where p.auditStatus=3 and isEffective=1) ";
		if (name != null && name != "") {
			sql += " and w.name like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Warehouse> result = query.getResultList();
		return result;
	}

	public Long gettotal(String name) {
		String sql = "select count(w) from Warehouse  w where w.productCode in(select p.code from Product p where p.auditStatus=3 and isEffective=1) ";
		if (name != null && name != "") {
			sql += " and w.name like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		return (Long) query.getSingleResult();
	}
}
