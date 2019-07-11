package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ArticleDao;
import com.entity.Article;
import com.entity.Special;
import com.util.Pageh;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	
	public void add(Article article) {
		articleDao.add(article);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
		articleDao.delete(id);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Article> select(Pageh page) {
		int pageNow = (page.getPageNow()-1)*page.getPageSize();
		page.setPageNow(pageNow);
		List<Article> list = articleDao.select(page);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pages) {
		int pageCount = 0;
		int rowCount = articleDao.gettotal(pages.getObject1());
		if ((rowCount % pages.getPageSize()) == 0) {
			pageCount = rowCount / pages.getPageSize();
		} else {
			pageCount = rowCount / pages.getPageSize() + 1;
		}
		return pageCount;
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Article updatequery(String code) {
		Article article = articleDao.updatequery(code);
		return article;
	}
	/**
	 * 查所有发布数据
	 * @param code
	 * @return
	 */
	public Article selectIs(int is) {
		Article article = articleDao.selectIs(is);
		return article;
	}
	public Article articleReport(String code) {
		Article article = articleDao.articleReport(code);
		return article;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Article article) {
		articleDao.updateSave(article);
	}
	public void updateIs(int article,String code) {
		articleDao.updateIs(article,code);
	}
	/**
	 * 找专题名
	 * 
	 * @param id
	 */
	public List<Special> specialName(){
	return articleDao.selectName();
	}
}
