package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pageh;
import com.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
	Role findByCode(String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update  Role set state=?1 where code =?2")
	void delete(String state,String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update  Role set name=?1 where code =?2")
	void update(String name,String code);
	Page<Role>findByNameContaining(String name, Pageable pageable);
}
