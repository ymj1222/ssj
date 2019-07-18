package com.dao;

import com.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductTypeDao extends JpaRepository<ProductType,Integer>, JpaSpecificationExecutor {
 @Modifying
 @Query(value = "update Products set type=null,producttype_id=null where type= ?1",nativeQuery = true)
 void updateproduct(Long code);
 @Modifying
 void deleteByCode(Long code);
 ProductType getByCode(Long code);
}
