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

import com.entity.Logistics;
import com.entity.Pageh;

@Repository
public interface LogisticsDao extends JpaRepository<Logistics, Integer> {
	Logistics findByCode(String code);
	Page<Logistics> findByNameContaining(String name, Pageable pageable);
}
