package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountRole;
import com.entity.RoleAuth;

@Repository
public class RoleAuthDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Transactional
	public void add(String roleCode,String authCode) {
		RoleAuth ra=new RoleAuth();
		ra.setAuthCode(authCode);
		ra.setRoleCode(roleCode);
		entityManager.persist(ra);
	}
	public List<RoleAuth> queryByRoleCode(String roleCode){
		String jpql=" select z from RoleAuth as z where z.roleCode=?1";
		Query query=entityManager.createQuery(jpql);
		return query.setParameter(1, roleCode).getResultList();
	}
	public Integer gettotal() {
		String jpql="select count(z) from RoleAuth as z";
		Query query =  entityManager.createQuery(jpql);
		Long l=(Long)query.getResultList().get(0);
		return l.intValue();
	}
	@Transactional
	public void update(RoleAuth roleAuth) {
		String jpql=" update RoleAuth set auth_code=?1 where roleCode=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1, roleAuth.getAuthCode()).setParameter(2,roleAuth.getRoleCode()).executeUpdate();
	}
	public List<RoleAuth> select(int pageNow,int pageSize){
		String jpql="select z from zj_role_auth as z ";
		Query query=entityManager.createQuery(jpql);
		return query.setMaxResults(pageSize).setFirstResult(pageNow).getResultList();
	}
	public void delete(String roleCode,String authCode) {
		String jpql="delete from zj_role_auth where roleCode=?1 and authCode=?2";
		Query query=entityManager.createQuery(jpql);
		query.setParameter(1,roleCode).setParameter(2, authCode).executeUpdate();
	}
}
