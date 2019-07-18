package com.dao;

import com.entity.ProductEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductEvaluationDao extends JpaRepository<ProductEvaluation,Integer>, JpaSpecificationExecutor {

}
