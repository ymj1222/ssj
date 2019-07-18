package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.BrowsingHistory;

@Repository
public interface BrowsingHistoryDao  extends JpaRepository<BrowsingHistory, Integer> {
	BrowsingHistory findByCode(String code);
	@Modifying
	@Transactional
	@Query("delete from BrowsingHistory b where b.code=?1")
	void deleteByCode(String code);
	Page<BrowsingHistory> findByUsersCode(String userCode, Pageable pageable);
	int countByUsersCode(String userCode);
}