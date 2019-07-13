package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entity.Agent;
import com.entity.Product;

@Repository
public class ProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 * 
	 * @param 商品添加方法
	 */
	public void insert(Product product) {
		entityManager.persist(product);
	}

	public List<Product> selectproductlist(String name, int pageNow, int pageSize) {
		String sql = "select p from Product p  where p.auditStatus in(1,2)  ";
		if (name != null && name != "") {
			sql += "and p.name like '%'||?1||'%'";
		}
		sql+="order by id desc";
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Product> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	};

	public Long selectproductlistcount(String name) {
		String sql = "select count(p) from Product p  where p.auditStatus in(1,2) ";
		if (name != null && name != "") {
			sql += "and p.name like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		return (Long) query.getSingleResult();
	}

	public Product updatequery(Long code) {
		String sql = "select p from Product p where p.code = ?1";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, code);
		return (Product) query.getSingleResult();
	}

	public void updateSave(Product product) {
		String sql = "update Product  set name= ?1,price= ?2,type= ?3,brandCode= ?4,size= ?5,sellValue= ?6,marketValue= ?7,color= ?8,induction= ?9 where code= ?10 ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, product.getName());
		query.setParameter(2, product.getPrice());
		query.setParameter(3, product.getType());
		query.setParameter(4, product.getBrandCode());
		query.setParameter(5, product.getSize());
		query.setParameter(6, product.getSellValue());
		query.setParameter(7, product.getMarketValue());
		query.setParameter(8, product.getColor());
		query.setParameter(9, product.getInduction());
		query.setParameter(10, product.getCode());
		query.executeUpdate();
	};

	public List<Product> querystatus(int status, int pageNow, int pageSize) {
		String sql = "select p from Product p where p.auditStatus= ?1 order by id desc";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, status);
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Product> result = query.getResultList();
		return result;
	};

	public Long querystatuscount(int status) {
		String sql = "select count(p) from Product p where p.auditStatus= ?1";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, status);
		return (Long) query.getSingleResult();
	};

	public void updateStatus(long status, long code) {
		System.out.println(status+".........."+code);
		String sql = "update Products set audit_Status= ?1 where code= ?2";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, status);
		query.setParameter(2, code);
		query.executeUpdate();
	}

	public void updateShelftime(Product product) {
		Query query = entityManager
				.createNativeQuery("update Products set shelftime= ?1,is_Effective= ?2 where code= ?3");
		query.setParameter(1, product.getShelftime());
		query.setParameter(2, product.getIsEffective());
		query.setParameter(3, product.getCode());
		query.executeUpdate();
	}

	public List<Product> both(Product product, int pageNow, int pageSize) {
		String sql = "select p from Product p where 1=1 ";
		if (product.getName() != null && product.getName() != "") {
			sql += " and p.name like '%'||?1||'%'";
		}
		if (product.getCode() != null && product.getCode() != 0) {
			sql += " and p.code= ?2";
		}
		if (product.getType() != null && product.getType() != 0) {
			sql += " and p.type= ?3";
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			sql += " and p.brandCode= ?4";
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			sql += " and p.auditStatus= ?5";
		}
		if (product.getIsEffective() != null) {
			sql += " and p.isEffective= ?6";
		}
		sql += " order by id desc";
		Query query = entityManager.createQuery(sql);
		if (product.getName() != null && product.getName() != "") {
			query.setParameter(1, product.getName());
		}
		if (product.getCode() != null && product.getCode() != 0) {
			query.setParameter(2, product.getCode());
		}
		if (product.getType() != null && product.getType() != 0) {
			query.setParameter(3, product.getType());
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			query.setParameter(4, product.getBrandCode());
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			query.setParameter(5, product.getAuditStatus());
		}
		if (product.getIsEffective() != null) {
			query.setParameter(6, product.getIsEffective());
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Product> result = query.getResultList();
		return result;
	}

	public Long bothtotal(Product product) {
		String sql = "select count(p) from Product p where 1=1 ";
		if (product.getName() != null && product.getName() != "") {
			sql += " and p.name like '%'||?1||'%'";
		}
		if (product.getCode() != null && product.getCode() != 0) {
			sql += " and p.code= ?2";
		}
		if (product.getType() != null && product.getType() != 0) {
			sql += " and p.type= ?3";
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			sql += " and p.brandCode= ?4";
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			sql += " and p.auditStatus= ?5";
		}
		if (product.getIsEffective() != null) {
			sql += " and p.isEffective= ?6";
		}
		Query query = entityManager.createQuery(sql);
		if (product.getName() != null && product.getName() != "") {
			query.setParameter(1, product.getName());
		}
		if (product.getCode() != null && product.getCode() != 0) {
			query.setParameter(2, product.getCode());
		}
		if (product.getType() != null && product.getType() != 0) {
			query.setParameter(3, product.getType());
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			query.setParameter(4, product.getBrandCode());
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			query.setParameter(5, product.getAuditStatus());
		}
		if (product.getIsEffective() != null) {
			query.setParameter(6, product.getIsEffective());
		}
		return (Long) query.getSingleResult();
	}

	public List<Product> selectoption() {
		Query query = entityManager.createQuery(
				"select p from Product p where p.auditStatus=3 and p.code=(select w.product_code from Warehouse w where w.amount > 0)");
		List<Product> result = query.getResultList();
		return result;
	}

	public List<Product> selecttoqsale() {
		Query query = entityManager.createQuery("select p from Product p where p.auditStatus=3 and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)");
		List<Product> result = query.getResultList();
		return result;
	}

	public List<Product> selecttoproject(Long code) {
		Query query = entityManager
				.createQuery("select p from Product p where p.type= ?1 and p.auditStatus = 3 and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)");
		query.setParameter(1, code);
		List<Product> result = query.getResultList();
		return result;
	}

	public List<Product> selecttoproduct(String name) {
		String sql = "select p from Product p where  p.auditStatus = 3 and p.name like '%'||?||'%' and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, name);
		List<Product> result = query.getResultList();
		return result;
	}
	public List<Product> selecttoproductByType(String name) {
		String sql = "select p from Product p where  p.auditStatus = 3 and p.name like '%'||?||'%' and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, name);
		List<Product> result = query.getResultList();
		return result;
	}
	public void down(int id) {
		Agent agent = entityManager.find(Agent.class, id);
		Query query = entityManager.createNativeQuery(
				"update Products set agent_Code= null,agent_id = null,is_Effective=0 where agent_Code= ?1");
		query.setParameter(1, Long.valueOf(agent.getCode()));
		query.executeUpdate();
	}
}
