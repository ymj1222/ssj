package com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AdvertisementClick;
import com.util.Pageh;
 
@Repository
public class AdvertisementClickDao {
 
	//通过 @PersistenceContext 注解来标记成员变量!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 分页查询所有数据
	 * @author  xiao七
	 * @return  List<AdvertisementClick>
	 */
	@SuppressWarnings("unchecked")//解决getResulltList();方法黄色标识问题
	public List<AdvertisementClick> getPersons(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {
			Query query = entityManager.createQuery("SELECT t  FROM AdvertisementClick t where t.advertisementCode like '%'||?1||'%' order by t.id desc");
			query.setParameter(1, pageh.getObject1());
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<AdvertisementClick> list = query.getResultList();
			return list;
			}else {
			Query query = entityManager.createQuery("SELECT t  FROM AdvertisementClick t");
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<AdvertisementClick> list = query.getResultList();
			return list;
			}
	}
	/**
	 * 获取数据总条数
	 * @author  xiao七
	 * @return  List<Object>
	 */
	public Integer gettotal(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {	
		Query query = entityManager.createQuery("SELECT count(t.id) FROM AdvertisementClick t where t.advertisementCode like '%'||?1||'%'");
		query.setParameter(1, pageh.getObject1());
		Long i = (Long) query.getResultList().get(0);
		return i.intValue();
		}else {
			Query query = entityManager.createQuery("SELECT count(t.id) FROM AdvertisementClick t ");
			Long i = (Long) query.getResultList().get(0);
			return i.intValue();
		}
	}
	/**
	 * 新增数据
	 * @param advertisementClick
	 */
	@Transactional
	public void add(AdvertisementClick advertisementClick) {
		entityManager.persist(advertisementClick);
	}
	/**
	 * 根据advertisementCode修改AdvertisementClick表里数据
	 * @author xiao七
	 * @param advertisementClick
	 */
	@Transactional
	public void update(AdvertisementClick advertisementClick) {
		String jpql = "UPDATE AdvertisementClick p SET p.cliclkFrequency= ?1 WHERE p.advertisementCode= ?2";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, advertisementClick.getCliclkFrequency());
		query.setParameter(2, advertisementClick.getAdvertisementCode());
		query.executeUpdate();
	}
	/**
	 * 根据AdvertisementCode获取要修改的数据
	 * @param advertisementClick
	 * @return
	 */
	public AdvertisementClick upquery(String code) {
			// 1、创建jpql语句(SELECT i 可以省去)
				String jpql = "SELECT p FROM AdvertisementClick p WHERE p.advertisementCode= ?1";
				// 2、创建查询对象
				Query query = entityManager.createQuery(jpql);
				// 3、设置查询参数，位置从1开始
				query.setParameter(1, code);
				AdvertisementClick item = (AdvertisementClick) query.getSingleResult();
				return item;
	}
	
	
}
