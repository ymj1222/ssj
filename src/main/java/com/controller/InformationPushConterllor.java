package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.InformationPush;
import com.service.InformationPushService;

@Controller
public class InformationPushConterllor {
	@Autowired
	private InformationPushService informationPushService;

	/**
	 * �ҵ����ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findinformationPushAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/informationPush/informationPushAdd.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findinformationPushList")
	public String findselect() {
		return "forward:/WEB-INF/views/informationPush/informationPushList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param informationPush
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/informationPushAdd", method = RequestMethod.POST)
	public String add(InformationPush informationPush, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		informationPush.setDate(DateUtils.getCurrentDateTime());
		informationPush.setCreateTime(DateUtils.getCurrentDateTime());
		informationPush.setCreator(name);
		informationPush.setUpdator(name);
		informationPush.setUpdateTime(DateUtils.getCurrentDateTime());
		informationPushService.add(informationPush);
		return "forward:/WEB-INF/views/informationPush/informationPushList.jsp";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/informationPushselect")
	public void select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String phone = request.getParameter("phone");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		Integer pageCount = 0;
		pageh.setPageNow((Integer.parseInt(pageNow) - 1) * Integer.parseInt(pageSize));
		pageh.setPageSize(Integer.parseInt(pageSize));
//		phone.replaceAll("_", "\\\\_");
		pageh.setPhone(SearchTool.decodeSpecialCharsWhenLikeUseSlash(phone));
		pageCount = informationPushService.gettotal(pageh);
		List<InformationPush> list = informationPushService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
	}
}
