package com.dao;

import com.entity.ArticleReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleReportDao extends JpaRepository<ArticleReport,Integer>, JpaSpecificationExecutor<ArticleReport> {
    Page<ArticleReport> getByArticleNameContainingOrderByIdDesc(String name, Pageable pageable);
}
