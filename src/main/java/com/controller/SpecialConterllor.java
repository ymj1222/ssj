package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Special;
import com.service.SpecialService;

@Controller
public class SpecialConterllor {
	@Autowired
	private SpecialService specialService;

	@RequestMapping("/finSpecialAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/special/specialAdd.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findSpecialList")
	public String findselect() {
		return "forward:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/specialAdd", method = RequestMethod.POST)
	public String add(Special special, Model model, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		special.setCode(code);
		special.setCreateTime(DateUtils.getCurrentDateString());
		special.setCreateor(name);
		special.setLastUpdateTime(DateUtils.getCurrentDateString());
		special.setLastUpdater(name);
		specialService.add(special);
		return "forward:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * ����idɾ������
	 * 
	 * @param
	 */
	@RequestMapping(value = "/specialDelete/{code}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("code") String code) {
		specialService.delete(code);
		return "redirect:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/specialSelect")
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
		pages.setObject1(SearchTool.decodeSpecialCharsWhenLikeUseSlash(sname));
		org.springframework.data.domain.Page<Special>page1 = specialService.select(pages);
		response.setCharacterEncoding("utf-8");
		page.setList(page1.getContent());
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	@RequestMapping("/finSpecialUpdate")
	public String findUpdate() {
		return "forward:/WEB-INF/views/special/specialUpdateSave.jsp";
	}

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/specialUpdate")
	public String update(String code, Model model) {
		Special special = specialService.updatequery(code);
		model.addAttribute("code", special.getCode());
		model.addAttribute("name", special.getName());
		model.addAttribute("type", special.getType());
		model.addAttribute("id", special.getId());
		model.addAttribute("createTime", special.getCreateTime());
		model.addAttribute("createor", special.getCreateor());
		return "forward:/WEB-INF/views/special/specialUpdateSave.jsp";
	}

	/**
	 * �޸�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/specialUpdateSave")
	public String updateSave(Special special, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		special.setLastUpdater(name);
		special.setLastUpdateTime(DateUtils.getCurrentDateString());
		specialService.update(special);
		return "redirect:findSpecialList";
	}

	@RequestMapping(value = "/specialUpdateQuery")
	public Special updateQuery(String code, HttpServletResponse response) throws IOException {
		Special p = specialService.updatequery(code);
		System.out.println(p);
		return p;
	}
}
