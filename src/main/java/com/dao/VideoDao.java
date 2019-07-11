package com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Video;
import com.util.Pageh;
 
@Repository
public class VideoDao {
 
	//ͨ�� @PersistenceContext ע������ǳ�Ա����!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * ��ҳ��ѯ��������
	 * @author  xiao��
	 * @return  List<Video>
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getPersons(Pageh pageh) {
		if(null!=pageh.getObject1()&& !pageh.getObject1().equals("")) {
			Query query = entityManager.createQuery("SELECT t  FROM Video t where t.name like '%'||?1||'%' order by t.id desc");
			query.setParameter(1, pageh.getObject1());
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<Video> list = query.getResultList();
			return list;
			}else {
			Query query = entityManager.createQuery("SELECT t  FROM Video t");
			query.setFirstResult(pageh.getPageNow());
			query.setMaxResults(pageh.getPageSize());
			List<Video> list = query.getResultList();
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
			Query query = entityManager.createQuery("SELECT count(t.id) FROM Video t where t.name like '%'||?1||'%'");
			query.setParameter(1,pageh.getObject1());
			Long i = (Long) query.getResultList().get(0);
			return i.intValue();
		}else {
			Query query = entityManager.createQuery("SELECT count(t.id) FROM Video t");
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
	public List<Video> getUrl() {
		Query query = entityManager.createQuery("SELECT t  FROM Video t where t.code != ''");
		List<Video> list = query.getResultList();
		return list;
	}
	/**
	 * ����codo�޸���Ƶ������
	 * @author xiao��
	 * @param photo
	 */
	@Transactional
	public void update(Video video) {
		String jpql = "UPDATE Video p SET p.name=?1 WHERE p.code= ?2";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, video.getName());
		query.setParameter(2, video.getCode());
		query.executeUpdate();
	}
	/**
	 * ��ȡҪ�޸ĵ����ݷ���video����
	 * @param video
	 * @return
	 */
	public Video upquery(String code) {
		// 1������jpql���(SELECT i ����ʡȥ)
		String jpql = "SELECT p FROM Video p WHERE p.code= ?1";
		// 2��������ѯ����
		Query query = entityManager.createQuery(jpql);
		// 3�����ò�ѯ������λ�ô�1��ʼ
		query.setParameter(1, code);
		Video item = (Video) query.getSingleResult();
		return item;
	}
	/**
	 * ����CODEɾ����Ƭ������
	 * @param photo
	 */
	@Transactional
	public void delete(String code) {
		String jpql = "delete from Video p WHERE p.code= ?1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, code);
		query.executeUpdate();
	}
	/**
	 * ��Ƶ����
	 * @param video��
	 */
	@Transactional
	public void add(Video video) {
		entityManager.persist(video);
	}
	
	
}
