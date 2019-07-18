package com.service;

import com.dao.ArticleReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.ArticleReport;
import com.util.Pageh;

@Service
public class ArticleReportService {
	@Autowired
	private ArticleReportDao articleReportDa;

	public void add(ArticleReport article) {
		articleReportDa.save(article);
	}
	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public Page<ArticleReport>select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			Page<ArticleReport> page=articleReportDa.getByArticleNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<ArticleReport> page=articleReportDa.findAll(pageable);
			return page;
		}
	}
}
