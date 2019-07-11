package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.ServiceRecord;
import com.entity.ShoppingGuide;
import com.util.Pageh;

/**
 * 服务记录dao
 * 
 * @author 小疯疯
 *
 */
@Repository
public class ServiceRecordDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 添加
	 * 
	 * 
	 */
	@Transactional
	public void add(ServiceRecord erviceRecord, int ShoppingGuideId) {
		ShoppingGuide shoppingGuide = entityManager.find(ShoppingGuide.class, ShoppingGuideId);
		erviceRecord.setShoppingGuideCode(shoppingGuide.getCode());
		erviceRecord.setShoppingGuideName(shoppingGuide.getName());
		erviceRecord.setShoppingGuide(shoppingGuide);
		entityManager.persist(erviceRecord);
	};

	/**
	 * 带分页的模糊搜索
	 * 
	 * @param pageh
	 * @return
	 */
	public List<ServiceRecord> select(Pageh pageh) {
		String jpql = "select t from ServiceRecord t where 1 = 1";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " and t.shoppingGuideName like '%'||?1||'%' ";

		}
		jpql += " order by t.id desc";
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}

		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<ServiceRecord> list = query.getResultList();
		return list;
	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public String gettotal(Pageh pageh) {
		String jpql = "select count(t) from ServiceRecord t where  1 = 1";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " and t.shoppingGuideName like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}

		Long count = (Long) query.getResultList().get(0);
		return count.toString();

	};

	@Transactional
	public void delete(int id) {
		String jpql1 = "update zj_service_record a set a.shoppingGuide_id = null where a.shoppingGuide_id = ?";
		Query query1 = entityManager.createNativeQuery(jpql1);
		query1.setParameter(1, id);
		query1.executeUpdate();
	}
}
