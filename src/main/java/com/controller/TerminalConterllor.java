package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Terminal;
import com.service.TerminalService;

@Controller
public class TerminalConterllor {
	@Autowired
	private TerminalService terminalService;

	/**
	 * 找到添加页码
	 * @return
	 */
	@RequestMapping("/findterminalAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/terminal/terminalAdd.jsp";
	}

	/**
	 * 找到列表页面
	 * @return
	 */
	@RequestMapping("/findterminalList")
	public String findselect() {
		return "forward:/WEB-INF/views/terminal/terminalList.jsp";
	}

	/***
	 * 添加
	 * @param ter
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/terminalAdd", method = RequestMethod.POST)
	public String add(Terminal ter, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		ter.setCode(code);
		ter.setCreator(name);
		ter.setUpdator(name);
		ter.setCreateTime(DateUtils.getCurrentDateTime());
		ter.setUpdateTime(DateUtils.getCurrentDateTime());
		terminalService.add(ter);
		return "forward:/WEB-INF/views/terminal/terminalList.jsp";
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/terminalDelete", method = RequestMethod.POST)
	public String delete(String id) {
		terminalService.delete(id);
		return "forward:/WEB-INF/views/terminal/terminalList.jsp";
	}

	/**
	 	列表显示
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/terminalselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		pageh.setPageNow(Integer.parseInt(pageNow) );
		pageh.setPageSize(Integer.parseInt(pageSize));
		pageh.setObject1(SearchTool.decodeSpecialCharsWhenLikeUseSlash(name));
		org.springframework.data.domain.Page<Terminal> page1= terminalService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(page1.getContent());
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	/**
	 * 得到要修改的数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("terminalUpdate")
	public String update(String id, Map<String, Object> map) {
		Terminal ter = terminalService.updatequery(id);
		map.put("ter", ter);
		return "forward:/WEB-INF/views/terminal/terminalupdateSave.jsp";
	}

	/**
	 * 修改数据
	 * @param ter
	 * @param request
	 * @return
	 */
	@RequestMapping("/terminalUpdateSave")
	public String updateSave(Terminal ter, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		ter.setUpdateTime(DateUtils.getCurrentDateTime());
		ter.setUpdator(name);
		terminalService.update(ter);
		return "forward:/WEB-INF/views/terminal/terminalList.jsp";
	}

	/**
	 * 终端列表下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/queryTerminalCode")
	public Page queryCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		List<Terminal> list = terminalService.queryCode();
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		return page;
	}
}
