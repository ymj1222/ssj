package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountRole;
import com.entity.RoleAuth;

@Repository
public interface RoleAuthDao extends JpaRepository<RoleAuth, Integer> {
	List<RoleAuth>findByRoleCode(String roleCode);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update RoleAuth set auth_code=?1 where roleCode=?2")
	void update(String authCode,String roleCode );
	RoleAuth findByRoleCodeAndAuthCode(String roleCode,String authCode);
	@Modifying
	@Transactional
	void deleteById(Integer id);
}
