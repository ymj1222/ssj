package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ArticleReportDao;
import com.entity.ArticleReport;
import com.util.Pageh;

@Service
public class ArticleReportService {
	@Autowired
	ArticleReportDao ad;
	public void add(ArticleReport article) {
		ad.add(article);
	}
	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<ArticleReport> select(Pageh page) {
		int pageNow = (page.getPageNow()-1)*page.getPageSize();
		page.setPageNow(pageNow);
		List<ArticleReport> list = ad.select(page);
		return list;
	}
	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh p) {
		int pageCount = 0;
		int rowCount = ad.gettotal(p.getObject1());
		if ((rowCount % p.getPageSize()) == 0) {
			pageCount = rowCount / p.getPageSize();
		} else {
			pageCount = rowCount / p.getPageSize() + 1;
		}
		return pageCount;
	}
}
