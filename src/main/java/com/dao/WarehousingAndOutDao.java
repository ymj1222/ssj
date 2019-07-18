package com.dao;

import com.entity.WarehousingAndOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

public interface WarehousingAndOutDao extends JpaSpecificationExecutor, JpaRepository<WarehousingAndOut,Integer> {
WarehousingAndOut getByOrdersCode(Long code);
@Modifying
   void deleteByOrdersCode(Long code);
}
