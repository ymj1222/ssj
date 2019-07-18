package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Pageh;
import com.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {
	Users findByCode(String code);
	Users findByAccount(String account);
	Page<Users> findByNameContaining(String name, Pageable pageable);
	@Modifying
	@Transactional
	@Query("update Users set name=?1,phone=?2,wechat=?3,sex=?4,city=?5,age=?6,levelMark=?7,goldCoin=?8,integral=?9 where code=?10")
	void update(String name,String phone,String wechat,String sex,String city,int age,int levelMark,int goldCoin,int integral,String code);
	@Modifying
	@Transactional
	@Query("update Users set state=?1 where code=?2")
	void delete(String state,String code);
}
