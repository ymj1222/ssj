package com.dao;

import com.entity.ShoppingGuide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingGuideDao extends JpaRepository<ShoppingGuide,Integer>, JpaSpecificationExecutor<ShoppingGuide> {
    ShoppingGuide getById(int id);
    ShoppingGuide getByCode(String code);
    Page<ShoppingGuide> getByNameContainingOrderByIdDesc(String name,Pageable Pageable);
}
