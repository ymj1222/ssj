package com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Photo;
import com.util.Pageh;
 
@Repository
public class PhotoDao {
 
	//ͨ�� @PersistenceContext ע������ǳ�Ա����!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * ��ҳ��ѯ��������
	 * @author  xiao��
	 * @return  List<Photo>
	 */
	@SuppressWarnings("unchecked")//���getResulltList();������ɫ��ʶ����
	public List<Photo> getPersons(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {
		Query query = entityManager.createQuery("SELECT t  FROM Photo t where t.name like '%'||?1||'%' order by t.id desc");
		query.setParameter(1, pageh.getObject1());
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<Photo> list = query.getResultList();
		return list;
		}else {
		Query query = entityManager.createQuery("FROM Photo order by id desc");
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<Photo> list = query.getResultList();
		return list;
		}
		// ��ҳ��ѯ���±�1��ʼ����ѯ2��
	}
	/**
	 * ��ȡ����������
	 * @author  xiao��
	 * @return  i
	 */
	public Integer gettotal(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {
			Query query = entityManager.createQuery("SELECT count(t.id) FROM Photo t where t.name like '%'||?1||'%'");
			query.setParameter(1, pageh.getObject1());
			Long i = (Long) query.getResultList().get(0);
			return i.intValue();	
		}else {
			Query query = entityManager.createQuery("SELECT count(t.id) FROM Photo t ");
			Long i = (Long) query.getResultList().get(0);
			return i.intValue();
		}
	}
	/**
	 * ��ѯ��Ʒcode��Ϊ�յ���������
	 * @author  xiao��
	 * @return  List<Photo>
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getUrl() {
		Query query = entityManager.createQuery("SELECT t  FROM Photo t where t.productCode!= ''");
		List<Photo> list = query.getResultList();
		return list;
	}
	/**
	 * ����codo�޸���Ƭ������
	 * @author xiao��
	 * @param photo
	 */
	@Transactional
	public void update(Photo photo) {
		String jpql = "UPDATE Photo p SET p.name= ?1 WHERE p.code= ?2";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, photo.getName());
		query.setParameter(2, photo.getCode());
		query.executeUpdate();
	}
	
	/**
	 * ��ȡҪ�޸ĵ����ݷ���photo����
	 * @param photo
	 * @return
	 */
	public Photo upquery(String code) {
		// 1������jpql���(SELECT i ����ʡȥ)
		String jpql = "SELECT p FROM Photo p WHERE p.code= ?1";
		// 2��������ѯ����
		Query query = entityManager.createQuery(jpql);
		// 3�����ò�ѯ������λ�ô�1��ʼ
		query.setParameter(1, code);
		Photo item = (Photo) query.getSingleResult();
		return item;
	}
	/**
	 * ����CODEɾ����Ƭ������
	 * @param code
	 */
	@Transactional
	public void delete(String code) {
		String jpql = "delete from Photo p WHERE p.code= ?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1,code);
		query.executeUpdate();
	}
	/**
	 * ͼƬ����
	 * @param photo
	 */
	@Transactional
	public void add(Photo photo) {
		entityManager.persist(photo);
	}
	/**
	 * ������Ƭ·����ȡ��Ƭ����
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getProduct(String url) {
		String jpql = "SELECT p FROM Photo p WHERE p.url= ?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, url);
		List<Photo> item = query.getResultList();
		return item;
	}
	/**
	 * ������Ƭ���е���Ʒcode��ѯ,��ѯ ��ƷͼƬ
	 * @param productCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getphotoUrl(String productCode) {
		String jpql = "SELECT p FROM Photo p WHERE p.productCode= ?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1,productCode);
		List<Photo> list = query.getResultList();
		return list;
	}
	
}
