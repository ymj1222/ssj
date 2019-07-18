package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.RouteCity;

@Repository
public interface RouteCityDao extends JpaRepository<RouteCity, Integer> {
	RouteCity findByWayBillNoAndCityCode(String wayBillNo,String ciyCode);
	int countByWayBillNo(String wayBillNo);
	RouteCity findByWayBillNo(String wayBillNo);
	@Modifying
	@Transactional
	void deleteById(Integer id);
	RouteCity findBySequenceAndWayBillNo(Integer sequence,String wayBillNo);
}
