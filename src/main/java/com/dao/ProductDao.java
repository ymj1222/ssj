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
			sql += "and p.name like '%'||?||'%'";
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
			sql += "and p.name like '%'||?||'%'";
		}
		Query query = entityManager.createQuery(sql);
		if (name != null && name != "") {
			query.setParameter(1, name);
		}
		return (Long) query.getSingleResult();
	}

	public Product updatequery(Long code) {
		String sql = "select p from Product p where p.code = :code";
		Query query = entityManager.createQuery(sql);
		query.setParameter("code", code);
		return (Product) query.getSingleResult();
	}

	public void updateSave(Product product) {
		String sql = "update Product  set name= :name,price= :price,type= :type,brandCode= :brandCode,size= :size,sellValue= :sell,marketValue= :market,color= :color,induction= :induction where code= :code ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("name", product.getName());
		query.setParameter("price", product.getPrice());
		query.setParameter("type", product.getType());
		query.setParameter("brandCode", product.getBrandCode());
		query.setParameter("size", product.getSize());
		query.setParameter("sell", product.getSellValue());
		query.setParameter("market", product.getMarketValue());
		query.setParameter("color", product.getColor());
		query.setParameter("induction", product.getInduction());
		query.setParameter("code", product.getCode());
		query.executeUpdate();
	};

	public List<Product> querystatus(int status, int pageNow, int pageSize) {
		String sql = "select p from Product p where p.auditStatus= :status order by id desc";
		Query query = entityManager.createQuery(sql);
		query.setParameter("status", status);
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Product> result = query.getResultList();
		return result;
	};

	public Long querystatuscount(int status) {
		String sql = "select count(p) from Product p where p.auditStatus= :status";
		Query query = entityManager.createQuery(sql);
		query.setParameter("status", status);
		return (Long) query.getSingleResult();
	};

	public void updateStatus(long status, long code) {
		String sql = "update Product set auditStatus= :status where code= :code";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("status", status);
		query.setParameter("code", code);
		query.executeUpdate();
	}

	public void updateShelftime(Product product) {
		Query query = entityManager
				.createNativeQuery("update Product set shelftime= :time,isEffective= :effective where code= :code");
		query.setParameter("time", product.getShelftime());
		query.setParameter("effective", product.getIsEffective());
		query.setParameter("code", product.getCode());
		query.executeUpdate();
	}

	public List<Product> both(Product product, int pageNow, int pageSize) {
		String sql = "select p from Product p where 1=1 ";
		if (product.getName() != null && product.getName() != "") {
			sql += " and p.name like '%'||?||'%'";
		}
		if (product.getCode() != null && product.getCode() != 0) {
			sql += " and p.code= :code";
		}
		if (product.getType() != null && product.getType() != 0) {
			sql += " and p.type= :type";
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			sql += " and p.brandCode= :brand";
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			sql += " and p.auditStatus= :status";
		}
		if (product.getIsEffective() != null) {
			sql += " and p.isEffective= :effective";
		}
		sql += " order by id desc";
		Query query = entityManager.createQuery(sql);
		if (product.getName() != null && product.getName() != "") {
			query.setParameter("name", product.getName());
		}
		if (product.getCode() != null && product.getCode() != 0) {
			query.setParameter("code", product.getCode());
		}
		if (product.getType() != null && product.getType() != 0) {
			query.setParameter("type", product.getType());
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			query.setParameter("brand", product.getBrandCode());
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			query.setParameter("status", product.getAuditStatus());
		}
		if (product.getIsEffective() != null) {
			query.setParameter("effective", product.getIsEffective());
		}
		query.setFirstResult(pageNow);
		query.setMaxResults(pageSize);
		List<Product> result = query.getResultList();
		return result;
	}

	public Long bothtotal(Product product) {
		String sql = "select count(p) from Product p where 1=1 ";
		if (product.getName() != null && product.getName() != "") {
			sql += " and p.name like '%'||?||'%'";
		}
		if (product.getCode() != null && product.getCode() != 0) {
			sql += " and p.code= :code";
		}
		if (product.getType() != null && product.getType() != 0) {
			sql += " and p.type= :type";
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			sql += " and p.brandCode= :brand";
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			sql += " and p.auditStatus= :status";
		}
		if (product.getIsEffective() != null) {
			sql += " and p.isEffective= :effective";
		}
		Query query = entityManager.createQuery(sql);
		if (product.getName() != null && product.getName() != "") {
			query.setParameter("name", product.getName());
		}
		if (product.getCode() != null && product.getCode() != 0) {
			query.setParameter("code", product.getCode());
		}
		if (product.getType() != null && product.getType() != 0) {
			query.setParameter("type", product.getType());
		}
		if (product.getBrandCode() != null && product.getBrandCode() != 0) {
			query.setParameter("brand", product.getBrandCode());
		}
		if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
			query.setParameter("status", product.getAuditStatus());
		}
		if (product.getIsEffective() != null) {
			query.setParameter("effective", product.getIsEffective());
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
				.createQuery("select p from Product p where p.type= :type and p.auditStatus = 3 and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)");
		query.setParameter("type", code);
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

	public void down(int id) {
		Agent agent = entityManager.find(Agent.class, id);
		Query query = entityManager.createNativeQuery(
				"update Product set agentCode= null,agent_id = null,isEffective=0 where agentCode= :code");
		query.setParameter("code", Long.valueOf(agent.getCode()));
		query.executeUpdate();
	}
}
