package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.ProductBrand;
import com.entity.Terminal;

@Repository
public class ProductBrandDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 *
	 * @param 商品品牌添加方法
	 */
	public void insert(ProductBrand productBrand) {
		entityManager.persist(productBrand);
	}

	/**
	 * 跟據code刪品牌
	 *
	 * @param code
	 */
	public void delete(long code) {
		Query querys = entityManager
				.createNativeQuery("update Product set brandCode=null,brand_id=null where brandCode= :code");
		querys.setParameter("code", code);
		querys.executeUpdate();
		Query query = entityManager.createNativeQuery("delete from ProductBrand where code= :code");
		query.setParameter("code", code);
		query.executeUpdate();
	}

	public List<ProductBrand> brand() {
		Query query = entityManager.createQuery("select b from ProductBrand b ");
		List<ProductBrand> result = query.getResultList();
		return result;
	}

	// String brandToName(@Param("code") long code);
	/**
	 * @param pageh
	 * @return全部的品牌
	 */
	public List<ProductBrand> select(String name, int pageNow, int pageSize) {
		String sql = "select b from ProductBrand b where 1=1 ";
		if (name != null && name != "") {
			sql += " and b.name like '%'||?||'%'";
		}
		sql+="order by b.id desc";
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<ProductBrand> result = query.getResultList();
		return result;
	}

	public Long total(String name) {
		String sql = "select count(b) from ProductBrand b where 1=1 ";
		if (name != null && name != "") {
			sql += " and b.name like '%'||?||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		return (Long) query.getSingleResult();
	}

	public ProductBrand select(long code) {
		Query query = entityManager.createQuery("select b from ProductBrand b where code= :code");
		query.setParameter("code", code);
		return (ProductBrand) query.getSingleResult();
	}
	public ProductBrand findbyid(int id) {
		return entityManager.find(ProductBrand.class, id);
		
	}
}