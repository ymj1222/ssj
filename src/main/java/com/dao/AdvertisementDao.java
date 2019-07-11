package com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Advertisement;
import com.util.Pageh;
 
@Repository
public class AdvertisementDao {
 
	//ͨ�� @PersistenceContext ע������ǳ�Ա����!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * ��ҳ��ѯ��������
	 * @author  xiao��
	 * @return  List<Advertisement>
	 */
	@SuppressWarnings("unchecked")//���getResulltList();������ɫ��ʶ����
	public List<Advertisement> getPersons(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {
			Query query = entityManager.createQuery("SELECT t  FROM Advertisement t where t.terminalName like '%'||?1||'%'  order by t.id desc ");
			query.setParameter(1, pageh.getObject1());
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<Advertisement> list = query.getResultList();
			return list;
			}else {
			Query query = entityManager.createQuery("SELECT t  FROM Advertisement t");
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<Advertisement> list = query.getResultList();
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
			Query query = entityManager.createQuery("SELECT count(t.id) FROM Advertisement t where t.terminalCode like '%'||?1||'%'");
			query.setParameter(1, pageh.getObject1());
			Long i = (Long) query.getResultList().get(0);
			return i.intValue();
		}else {
		Query query = entityManager.createQuery("SELECT count(t.id) FROM Advertisement t"); 
		Long i = (Long) query.getResultList().get(0);
		return i.intValue();
		}
	}
	/**
	 * ����codo�޸Ĺ���������
	 * @author xiao��
	 * @param advertisement
	 */
	@Transactional
	public void update(Advertisement advertisement) {
		String jpql = "UPDATE Advertisement p SET p.photo= ?1,p.video= ?2 WHERE p.code= ?3";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, advertisement.getPhoto());
		query.setParameter(2, advertisement.getVideo());

		query.setParameter(3, advertisement.getCode());
		query.executeUpdate();
	}
	/**
	 * ��ȡҪ�޸ĵ����ݷ���advertisement����
	 * @param advertisement
	 * @return
	 */
	public Advertisement upquery(String code) {
		// 1������jpql���(SELECT i ����ʡȥ)
		String jpql = "SELECT p FROM Advertisement p WHERE p.code= ?1";
		// 2��������ѯ����
		Query query = entityManager.createQuery(jpql);
		// 3�����ò�ѯ������λ�ô�1��ʼ
		query.setParameter(1, code);
		Advertisement item = (Advertisement) query.getSingleResult();
		return item;
	}
	
	
	/**
	 * ����idɾ�������������,����ɾ������������������
	 * @param advertisement
	 */
	@Transactional
	public void delete(String code) {
		String jpql = "SELECT p FROM Advertisement p WHERE p.code= ?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, code);
		Advertisement item = (Advertisement) query.getSingleResult();
		entityManager.remove(item);
	}
	/**
	 * ��������
	 * @param advertisement
	 */
	@Transactional
	public void add(Advertisement advertisement) {
		entityManager.persist(advertisement);
	}
	/**
	 * ����ͼƬ·����ȡAdvertisement����
	 * @param photo
	 * @return
	 */
	public Advertisement getquery(String photo) {
		// 1������jpql���(SELECT i ����ʡȥ)
		String jpql = "SELECT p FROM Advertisement p WHERE p.photo= ?1";
		// 2��������ѯ����
		Query query = entityManager.createQuery(jpql);
		// 3�����ò�ѯ������λ�ô�1��ʼ
		query.setParameter(1, photo);
		Advertisement item = (Advertisement) query.getSingleResult();
		return item;
	}
	/**
	 * ��ҳ�����ѯAdvertisement�����ǰ��������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Advertisement> getList() {
		Query query = entityManager.createQuery("SELECT t  FROM Advertisement t order by t.id desc");
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Advertisement> list = query.getResultList();
		return list;
	}
	
}
