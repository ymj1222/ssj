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
 
	//通过 @PersistenceContext 注解来标记成员变量!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 分页查询所有数据
	 * @author  xiao七
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
	 * 获取数据总条数
	 * @author  xiao七
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
	 * 查询商品code不为空的所有数据
	 * @author  xiao七
	 * @return  List<Photo>
	 */
	@SuppressWarnings("unchecked")
	public List<Video> getUrl() {
		Query query = entityManager.createQuery("SELECT t  FROM Video t where t.code != ''");
		List<Video> list = query.getResultList();
		return list;
	}
	/**
	 * 根据codo修改视频库数据
	 * @author xiao七
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
	 * 获取要修改的数据返回video对象
	 * @param video
	 * @return
	 */
	public Video upquery(String code) {
		// 1、创建jpql语句(SELECT i 可以省去)
		String jpql = "SELECT p FROM Video p WHERE p.code= ?1";
		// 2、创建查询对象
		Query query = entityManager.createQuery(jpql);
		// 3、设置查询参数，位置从1开始
		query.setParameter(1, code);
		Video item = (Video) query.getSingleResult();
		return item;
	}
	/**
	 * 根据CODE删除照片库数据
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
	 * 视频新增
	 * @param video；
	 */
	@Transactional
	public void add(Video video) {
		entityManager.persist(video);
	}
	
	
}
