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
 
	//ͨ�� @PersistenceContext ע������ǳ�Ա����!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * ��ҳ��ѯ��������
	 * @author  xiao��
	 * @return  List<AdvertisementClick>
	 */
	@SuppressWarnings("unchecked")//���getResulltList();������ɫ��ʶ����
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
	 * ��ȡ����������
	 * @author  xiao��
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
	 * ��������
	 * @param advertisementClick
	 */
	@Transactional
	public void add(AdvertisementClick advertisementClick) {
		entityManager.persist(advertisementClick);
	}
	/**
	 * ����advertisementCode�޸�AdvertisementClick��������
	 * @author xiao��
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
	 * ����AdvertisementCode��ȡҪ�޸ĵ�����
	 * @param advertisementClick
	 * @return
	 */
	public AdvertisementClick upquery(String code) {
			// 1������jpql���(SELECT i ����ʡȥ)
				String jpql = "SELECT p FROM AdvertisementClick p WHERE p.advertisementCode= ?1";
				// 2��������ѯ����
				Query query = entityManager.createQuery(jpql);
				// 3�����ò�ѯ������λ�ô�1��ʼ
				query.setParameter(1, code);
				AdvertisementClick item = (AdvertisementClick) query.getSingleResult();
				return item;
	}
	
	
}
