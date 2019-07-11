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
 
	//通过 @PersistenceContext 注解来标记成员变量!
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 分页查询所有数据
	 * @author  xiao七
	 * @return  List<Advertisement>
	 */
	@SuppressWarnings("unchecked")//解决getResulltList();方法黄色标识问题
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
	 * 获取数据总条数
	 * @author  xiao七
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
	 * 根据codo修改广告表里数据
	 * @author xiao七
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
	 * 获取要修改的数据返回advertisement对象
	 * @param advertisement
	 * @return
	 */
	public Advertisement upquery(String code) {
		// 1、创建jpql语句(SELECT i 可以省去)
		String jpql = "SELECT p FROM Advertisement p WHERE p.code= ?1";
		// 2、创建查询对象
		Query query = entityManager.createQuery(jpql);
		// 3、设置查询参数，位置从1开始
		query.setParameter(1, code);
		Advertisement item = (Advertisement) query.getSingleResult();
		return item;
	}
	
	
	/**
	 * 根据id删除广告表里的数据,级联删除广告点击次数表的数据
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
	 * 新增数据
	 * @param advertisement
	 */
	@Transactional
	public void add(Advertisement advertisement) {
		entityManager.persist(advertisement);
	}
	/**
	 * 根据图片路径获取Advertisement对象
	 * @param photo
	 * @return
	 */
	public Advertisement getquery(String photo) {
		// 1、创建jpql语句(SELECT i 可以省去)
		String jpql = "SELECT p FROM Advertisement p WHERE p.photo= ?1";
		// 2、创建查询对象
		Query query = entityManager.createQuery(jpql);
		// 3、设置查询参数，位置从1开始
		query.setParameter(1, photo);
		Advertisement item = (Advertisement) query.getSingleResult();
		return item;
	}
	/**
	 * 分页倒叙查询Advertisement表里的前七条数据
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
