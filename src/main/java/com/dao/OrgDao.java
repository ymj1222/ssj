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

import com.entity.Org;
import com.entity.Pageh;

@Repository
public interface OrgDao extends JpaRepository<Org, Integer> {
	Page<Org>findByNameContaining(String name, Pageable pageable);
	Org findByCode(String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query(" update Org set name=?1,managerCode=?2,pCode=?3 where code=?4")
	void update(String name,String managerCode,String pCode,String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update Org set state=?1 where code=?2")
	void enable(String state,String code);
}
