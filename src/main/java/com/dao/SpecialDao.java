package com.dao;

import com.entity.Article;
import com.entity.Special;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecialDao extends JpaRepository<Special,Integer>, JpaSpecificationExecutor<Special> {
    Special getByCode(String code);
    Page<Special> getByNameContainingOrderByIdDesc(String name, Pageable pageable);
}
