package com.dao;

import com.entity.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductBrandDao extends JpaRepository<ProductBrand,Integer>, JpaSpecificationExecutor {
    @Modifying
    @Query(value = "update Products set brand_Code=null,brand_id=null where brand_Code= ?1",nativeQuery = true)
    void updateproduct(Long code);
    @Modifying
    void deleteByCode(Long code);
    ProductBrand getProductBrandByCode(Long code);
}
