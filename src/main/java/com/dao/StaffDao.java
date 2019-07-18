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
import com.entity.Staff;

@Repository
public interface StaffDao extends JpaRepository<Staff, Integer> {

	Staff findByCode(String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update Staff set name=?1,org_code=?2,state=?3,tel=?4,birthday=?5 where code=?6")
	void update(String name,String orgCode,Integer state,String tel,String birthday,String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update Staff set state=?1 where code=?2")
	void enable(String state,String code);
	Page<Staff>findByNameContaining(String name, Pageable pageable);
}
