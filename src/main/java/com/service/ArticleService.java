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
	 * ����idɾ��
	 * 
	 * @param id
	 */
	public void delete(String id) {
		articleDao.delete(id);
	}

	/**
	 * �õ���������
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
	 * �õ�������ҳ��
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
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Article updatequery(String code) {
		Article article = articleDao.updatequery(code);
		return article;
	}
	/**
	 * �����з�������
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
	 * �޸�����
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
	 * ��ר����
	 * 
	 * @param id
	 */
	public List<Special> specialName(){
	return articleDao.selectName();
	}
}
