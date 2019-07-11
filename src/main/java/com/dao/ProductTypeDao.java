package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.ProductType;

@Repository
public class ProductTypeDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 *
	 * @param 商品類型添加方法
	 */
	public void insert(ProductType procductType) {
		entityManager.persist(procductType);
	}

	/**
	 * 跟據code刪種類
	 *
	 * @param code
	 */
	public void delete(long code) {
		Query querys = entityManager
				.createNativeQuery("update Products set type=null,producttype_id=null where type= ?1");
		querys.setParameter(1, code);
		querys.executeUpdate();
		Query query = entityManager.createNativeQuery("delete from Producttype where code= ?1");
		query.setParameter(1, code);
		query.executeUpdate();
	}

	/**
	 *
	 * @return 選項
	 */
	public List<ProductType> selectoption() {
		Query query = entityManager.createQuery("select t from ProductType t");
		List<ProductType> result = query.getResultList();
		return result;
	}

	public List<ProductType> select(String name, int pageNow, int pageSize) {
		String sql = "select t from ProductType t ";
		if (name != null && name != "") {
			sql += " where t.name like '%'||?1||'%'";
		}
		sql += "order by t.id desc";
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<ProductType> result = query.getResultList();
		return result;
	}

	public Long gettotal(String name) {
		String sql = "select count(t) from ProductType t ";
		if (name != null && name != "") {
			sql += " where t.name like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		return (Long) query.getSingleResult();
	}

	//
	// List<ProductType> selectType();
	//
	// String typeToName(@Param("code") long code);
	public ProductType select(long code) {
		Query query = entityManager.createQuery("select t from ProductType t where t.code= :code");
		query.setParameter("code", code);
		return (ProductType) query.getSingleResult();
	}
}
