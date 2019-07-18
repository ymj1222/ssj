package com.dao;

import com.entity.ServiceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceReCordDao extends JpaRepository<ServiceRecord,Integer>, JpaSpecificationExecutor<ServiceRecord> {
    Page<ServiceRecord> getByShoppingGuideNameContainingOrderByIdDesc(String name, Pageable pageable);
}
