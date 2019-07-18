package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
	City findByCode(Integer code);
}
