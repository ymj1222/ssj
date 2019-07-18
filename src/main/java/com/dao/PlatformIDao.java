package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.PlatformI;

@Repository
public interface PlatformIDao extends JpaRepository<PlatformI, Integer> {

	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("update PlatformI set logo=?1,companyWebsite=?2,tel=?3 where code=?4")
	void update(String logo,String companyWebsite,String tel,String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query(" update PlatformI set qr=?1,companyWebsite=?2,tel=?3 where code=?4")
	void update1(String qr,String companyWebsite,String tel,String code);
	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query("  update PlatformI set logo=?1,companyWebsite=?2,tel=?3,qr=?4 where code=?5")
	void update2(String logo,String companyWebsite,String tel,String qr,String code);
}
