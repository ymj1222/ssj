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
 
	//通过 @PersistenceContext 注解来标记成员变量!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 分页查询所有数据
	 * @author  xiao七
	 * @return  List<Photo>
	 */
	@SuppressWarnings("unchecked")//解决getResulltList();方法黄色标识问题
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
		// 分页查询，下标1开始，查询2条
	}
	/**
	 * 获取数据总条数
	 * @author  xiao七
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
	 * 查询商品code不为空的所有数据
	 * @author  xiao七
	 * @return  List<Photo>
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getUrl() {
		Query query = entityManager.createQuery("SELECT t  FROM Photo t where t.productCode!= ''");
		List<Photo> list = query.getResultList();
		return list;
	}
	/**
	 * 根据codo修改照片库数据
	 * @author xiao七
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
	 * 获取要修改的数据返回photo对象
	 * @param photo
	 * @return
	 */
	public Photo upquery(String code) {
		// 1、创建jpql语句(SELECT i 可以省去)
		String jpql = "SELECT p FROM Photo p WHERE p.code= ?1";
		// 2、创建查询对象
		Query query = entityManager.createQuery(jpql);
		// 3、设置查询参数，位置从1开始
		query.setParameter(1, code);
		Photo item = (Photo) query.getSingleResult();
		return item;
	}
	/**
	 * 根据CODE删除照片库数据
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
	 * 图片新增
	 * @param photo
	 */
	@Transactional
	public void add(Photo photo) {
		entityManager.persist(photo);
	}
	/**
	 * 根据照片路径获取照片对象
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
	 * 根据照片表中的商品code查询,查询 商品图片
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
