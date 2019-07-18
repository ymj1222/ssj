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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Article;
import com.entity.Special;
import com.service.ArticleService;
import com.service.SpecialService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;

@Controller
public class ArticleConterllor {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SpecialService ss;
	@RequestMapping("/finArticleAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/article/articleAdd.jsp";
	}
	@RequestMapping("/finArticleReport")
	public String findReport(String code, Model model,HttpServletRequest request) {
		
		Article a= articleService.articleReport(code);
		model.addAttribute("creator",GetNameUtil.getName(request));
		model.addAttribute("lastUpdater",GetNameUtil.getName(request));
		model.addAttribute("name",a.getName());
		model.addAttribute("content",a.getContent());
		
		return "forward:/WEB-INF/views/article/articleReport.jsp";
	}
	@RequestMapping("/finmyArticleReport")
	public String findmyReport(String code, Model model,HttpServletRequest request) {
		Article a= articleService.articleReport(code);
		model.addAttribute("creator",GetNameUtil.getName(request));
		model.addAttribute("lastUpdater",GetNameUtil.getName(request));
		model.addAttribute("name",a.getName());
		model.addAttribute("content",a.getContent());
		
		return "forward:/WEB-INF/views/article/myArticleReport.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findArticleList")
	public String findselect() {
		return "forward:/WEB-INF/views/article/articleList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/articleAdd", method = RequestMethod.POST)
	public String add(Article article, Model model,HttpServletRequest request) {
		long bs = System.currentTimeMillis();
		article.setCreateor(GetNameUtil.getName(request));
		article.setLastUpdater(GetNameUtil.getName(request));
		String code = Long.toString(bs);
		article.setCode(code);
		article.setIssue(0);
		article.setCreateTime(DateUtils.getCurrentDateString());
		article.setReleaseTime(DateUtils.getCurrentDateString());
		article.setLastUpdateTime(DateUtils.getCurrentDateString());
		Set<Article>list=new HashSet<>();
		list.add(article);
		articleService.add(article);
		return "redirect:findArticleList";
	}

	/**
	 * ����idɾ������
	 * 
	 * @param
	 */
	@RequestMapping(value = "/articleDelete")
	public String delete(String code) {
		articleService.delete(code);
		return "redirect:findArticleList";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/articleSelect")
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
		int pageCount = 0;
		Pageh pages=new Pageh();
		pages.setPageNow(Integer.parseInt(pageNow));
		pages.setPageSize(Integer.parseInt(pageSize));
		pages.setObject1(sname);
		org.springframework.data.domain.Page<Article> page1 = articleService.select(pages);
		response.setCharacterEncoding("utf-8");
		page.setList(page1.getContent());
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	@RequestMapping("/finArticleUpdate")
	public String findUpdate(Article article,HttpServletRequest request) {
		request.setAttribute("code", article.getCode());
		return "forward:/WEB-INF/views/article/articleUpdateSave.jsp";
	}

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/articleUpdate")
	public String update(String code, Model model) {
		Article article = articleService.updatequery(code);
		model.addAttribute("code", article.getCode());
		model.addAttribute("name", article.getName());
		model.addAttribute("content", article.getContent());
		model.addAttribute("id", article.getId());
		model.addAttribute("issue", article.getIssue());
		model.addAttribute("createor", article.getCreateor());
		model.addAttribute("createTime", article.getCreateTime());
		model.addAttribute("special", article.getSpecial());
		model.addAttribute("specialCode", article.getSpecialCode());
		model.addAttribute("specialName", article.getSpecialName());
		model.addAttribute("releaseTime", article.getReleaseTime());

		return "forward:/WEB-INF/views/article/articleUpdateSave.jsp";
	}

	/**
	 * �޸�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value="/articleUpdateSave")
	public String updateSave(Article article,HttpServletRequest request) {
		System.out.println(article.toString());
		article.setLastUpdater(GetNameUtil.getName(request));
		article.setLastUpdateTime(DateUtils.getCurrentDateString());
		articleService.update(article);
		return "redirect:findArticleList";
	}

	@RequestMapping(value = "/articleUpdateQuery")
	public Article updateQuery(String code, HttpServletResponse response) throws IOException {
		Article p = articleService.updatequery(code);
		return p;
	}
	/**
	 * �����з���������
	 * @param
	 * @param response
	 * @return 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/selectIs")
	public Article selectIs( HttpServletResponse response) throws IOException {
		Article p = articleService.selectIs(1);
		return p;
	}
	/**
	 * ��������
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/articleUpdateIs")
	public String updateIs(String code) {
		int article=1;
		articleService.updateIs(article,code);
		return "redirect:findArticleList";
	}
	@ResponseBody
	@RequestMapping(value = "/articleReport")
	public Article articleReport(String code, Model model) {
		Article a= articleService.articleReport(code);
		return a;
	}
	
	/**
	 * ר�����ͱ��
	 * 
	 * @return
	 * @throws                            
	 */
	@ResponseBody
	@RequestMapping("/specialName")
	public List<Special> specialType(HttpServletResponse response) throws IOException {
		List<Special> list = articleService.specialName();
		response.setCharacterEncoding("utf-8");
		return list;
	}
}