package com.dao;

import com.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {
    Article getByCode(String code);
    Page<Article> getByNameContainingOrderByIdDesc(String name, Pageable pageable);
    List<Article> getByIssue(int issue);
}
