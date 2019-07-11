package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.InformationPush;


@Repository
public interface InformationPushDao extends JpaRepository<InformationPush,Integer> {

	@Query(value = "select * from zj_information_push order by id desc limit ?,?",nativeQuery = true)
	List<InformationPush> select(int page ,int pagesize);

	@Query(value="select * from zj_information_push where phone like %?% order by id desc limit ?,?",nativeQuery = true)
	List<InformationPush> selecttwo(String phone,int page ,int pagesize);
//	/**
//	 * 带分页的模糊搜索
//	 *
//	 * @param pageh
//	 * @return
//	 */
//	public List<InformationPush> select(Pageh pageh) {
//		String jpql = "select t from InformationPush t";
//		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
//			jpql += " where t.phone like '%'||?||'%/' ";
//		}
//		jpql += " order by t.id desc";
//		Query query = entityManager.createQuery(jpql);
//		if (pageh.getPhone() != null && !pageh.getPhone().equals("")) {
//			query.setParameter(1, pageh.getPhone());
//		}
//		query.setFirstResult(pageh.getPageNow());
//		query.setMaxResults(pageh.getPageSize());
//		List<InformationPush> list = query.getResultList();
//		return list;
//	};

//	/**
//	 * 得到数据条数
//	 *
//	 * @return
//	 */
//	public String gettotal(Pageh pageh) {
//		String jpql = "select count(t) from InformationPush t";
//		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
//			jpql += " where t.phone like '%'||?||'%/'";
//		}
//		Query query = entityManager.createQuery(jpql);
//		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
//			query.setParameter(1, pageh.getObject1());
//		}
//		Long count = (Long) query.getResultList().get(0);
//		return count.toString();
//	};
		@Query(value = "select count(*) from zj_information_push",nativeQuery = true)
		int gettotal();

		@Query(value = "select count(*) from zj_information_push where phone like %?% ",nativeQuery = true)
		int gettotaltwo(String phone);
}
