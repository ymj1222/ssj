package com.service;

import java.util.List;

import com.dao.ArticleDao;
import com.dao.SpecialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Article;
import com.entity.Special;
import com.util.Pageh;

@Service
public class ArticleService {


	@Autowired
	private ArticleDao articleDa;


	@Autowired
	private SpecialDao specialDao;
	
	public void add(Article article) {
		articleDa.save(article);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
		articleDa.delete(articleDa.getByCode(id));
	}

	/**
	 * 得到所有数据
	 *
	 * @return
	 */
	public Page<Article >select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			org.springframework.data.domain.Page<Article> page=articleDa.getByNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<Article> page=articleDa.findAll(pageable);
			return page;
		}
	}



	public Article updatequery(String code) {
		Article article = articleDa.getByCode(code);
		return article;
	}

	public Article selectIs(int is) {
		List<Article>article= articleDa.getByIssue(is);
		if(article.size()>0){
			return article.get(0);
		}
		return null;
	}
	public Article articleReport(String code) {
		Article article = articleDa.getByCode(code);
		return article;
	}

	/**
	 * 修改数据
	 * 
	 * @param
	 * @param
	 */
	public void update(Article article) {
		articleDa.save(article);
	}
	public void updateIs(int article,String code) {
		Article article1 =articleDa.getByCode(code);
		if(article1!=null){
			article1.setIssue(article);
			articleDa.save(article1);
		}
	}
	/**
	 * 找专题名
	 * 
	 * @param
	 */
	public List<Special> specialName(){
	return specialDao.findAll();
	}
}
