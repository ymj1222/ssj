package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pageh;
import com.entity.Staff;

@Repository
public class StaffDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(Staff staff) {
		entityManager.persist(staff);
	}
	public Staff queryByCode(String code) {
		String jpql="select s from Staff as s where s.code=?1";
		Query query=entityManager.createQuery(jpql);
		return (Staff) query.setParameter(1, code).getResultList().get(0);
	}
	@Transactional
	public void update(Staff staff) {
		String jpql=" update Staff set name=?1,org_code=?2,state=?3,tel=?4,birthday=?5 where code=?6";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, staff.getName()).setParameter(2, staff.getOrgCode()).setParameter(3, staff.getState()).setParameter(4, staff.getTel()).setParameter(5, staff.getBirthday()).setParameter(6,staff.getCode()).executeUpdate();
	}
	@Transactional
	public void delete(String code) {
		String jpql="delete from Staff where code = ?1";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1,code).executeUpdate();
	}
	public List<Staff> select(Pageh ph){
		if(""!=ph.getObject1()) {
			String jpql="select a from Staff as a where a.name like '%'||?1||'%' order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).setParameter(1, ph.getObject1()).getResultList();
		}else {
			String jpql="select a from Staff as a  order by a.id desc ";
			Query query =  entityManager.createQuery(jpql);
			return query.setMaxResults(ph.getPageSize()).setFirstResult(ph.getPageNow()).getResultList();
			
		}
	}
	public List<Staff> query(){
		String jpql=" select s from Staff as s";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
	}
	public Integer gettotal(Pageh ph) {
		if(null!=ph.getObject1()) {
			String jpql="select count(z) from Staff as z where z.name like '%'||?1||'%'";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long)query.setParameter(1, ph.getObject1()).getResultList().get(0);
			return l.intValue();
		}else {
			String jpql="select count(z) from Staff as z";
			Query query =  entityManager.createQuery(jpql);
			Long l=(Long) query.getResultList().get(0);
			return  l.intValue();
		}
	}
	@Transactional
	public void enable(String state,String code) {
		String jpql="update Staff set state=?1 where code=?2";
		 Query query=entityManager.createQuery(jpql);
		 query.setParameter(1,state).setParameter(2, code).executeUpdate();
	}
}
