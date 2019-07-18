package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pay;

@Repository
public interface PayDao extends JpaRepository<Pay, Integer> {
    @Query(value="select * from zj_pay order by code asc ",nativeQuery = true)
	List<Pay>findOrderByIdDesc();
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update Pay set code=?1 where code=?2")
	void update(String code,String code1);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update Pay set code=?1 where code=?2 and name=?3")
	void update1(String code,String code1,String name);
}
