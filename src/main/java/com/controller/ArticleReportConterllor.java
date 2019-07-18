package com.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Article;
import com.entity.ArticleReport;
import com.service.ArticleReportService;
import com.service.ArticleService;
import com.util.DateUtils;
import com.util.Page;
import com.util.Pageh;

@Controller
public class ArticleReportConterllor {
	@Autowired
	ArticleReportService as;
	@Autowired
	ArticleService articles;

	@RequestMapping(value = "/articleReportAdd")
	public String add(ArticleReport article, Model model) {
		article.setReportingDate(DateUtils.getCurrentDateString());
		article.setCreateTime(DateUtils.getCurrentDateString());
		article.setLastUpdateTime(DateUtils.getCurrentDateString());
		as.add(article);
		return "redirect:findArticleList";
	}

	@RequestMapping(value = "/myArticleReportAdd")
	public String myOrdersadd(String articleCode, String type, String context,String articleName, Model model) {
		ArticleReport article = new ArticleReport();
		article.setArticleCode(articleCode);
		article.setContext(context);
		article.setType(type);
		article.setArticleName(articleName);
		Article a = articles.updatequery(articleCode);
		article.setReportingDate(DateUtils.getCurrentDateString());
		article.setCreateTime(DateUtils.getCurrentDateString());
		article.setLastUpdateTime(DateUtils.getCurrentDateString());
		Set<ArticleReport> list = new HashSet<>();
		list.add(article);
		a.setArticleReport(list);
		as.add(article);
		return "forward:qindex.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findArticleReportList")
	public String findselect() {
		return "forward:/WEB-INF/views/article/articleReportList.jsp";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/articleReportSelect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		String sname = request.getParameter("sname");
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		Pageh pages = new Pageh();
		pages.setPageNow(Integer.parseInt(pageNow));
		pages.setPageSize(Integer.parseInt(pageSize));
		pages.setObject1(sname);
		org.springframework.data.domain.Page<ArticleReport>page1 = as.select(pages);

		response.setCharacterEncoding("utf-8");
		page.setList(page1.getContent());
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
