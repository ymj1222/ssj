package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Route;

@Repository
public interface RouteDao extends JpaRepository<Route, Integer> {
	Route findByCode(String code);
	@Modifying
	@Transactional
	void deleteById(Integer id);
}
