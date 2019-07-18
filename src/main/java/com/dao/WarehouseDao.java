package com.dao;

import com.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseDao extends JpaRepository<Warehouse,Integer>, JpaSpecificationExecutor {
    Warehouse getByProductCode(Long code);
    @Modifying
    @Query(value = "update Warehouse set amount= ?1 where productCode= ?2",nativeQuery = true)
    void stockOut(Long code, Long amount);
    Warehouse getById(Integer id);
    @Modifying
    @Query(value = "update Warehouse set amount= ?1 where id= ?2",nativeQuery = true)
    void addamount(Integer amount, Integer id);
}
