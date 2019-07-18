package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.AccountAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Auth;
import com.entity.Pageh;

@Repository
public interface AuthDao  extends JpaRepository<Auth, Integer> {

	Auth findByCodeAndIsDelete(String code,String isDelete);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update  Auth set isDelete=?1 where code = ?2")
	void delete(String isDelete,String code);
	@org.springframework.data.jpa.repository.Query("select z from Auth as z where z.pCode is null or z.pCode=?1")
	List<Auth>queryPcode(String pCode);
List<Auth>findByPCode(String pCode);
	Page<Auth> findByNameContaining(String name, Pageable pageable);
	List<Auth>findAll();

	@Query(" update Auth set name=?1,url=?2,isBase=?3,menuType=?4,descri=?5 where code=?6")
	void update(String name,String url,String isBase,String menuType,String descri,String code);
}
