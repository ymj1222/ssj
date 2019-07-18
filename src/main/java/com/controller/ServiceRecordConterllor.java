package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.SearchTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ServiceRecord;
import com.service.ServiceRecordService;
import com.util.Page;
import com.util.Pageh;

@Controller
public class ServiceRecordConterllor {
	@Autowired
	private ServiceRecordService serviceRecordService;

	/**
	 * ????б????
	 * 
	 * @return
	 */
	@RequestMapping("/findserviceRecordList")
	public String findselect() {
		return "forward:/WEB-INF/views/serviceRecord/serviceRecordList.jsp";
	}

	/**
	 * ???????????
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/serviceRecordselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String shopp = request.getParameter("shopp");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		pageh.setPageNow(Integer.parseInt(pageNow));
		pageh.setPageSize(Integer.parseInt(pageSize));
		pageh.setObject1(SearchTool.decodeSpecialCharsWhenLikeUseSlash(shopp));
		org.springframework.data.domain.Page<ServiceRecord> page1= serviceRecordService.select(pageh);
		List<ServiceRecord> list = page1.getContent();
				for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStaisfaction().equals("3001")) {
				list.get(i).setStaisfaction("极差");
			} else if (list.get(i).getStaisfaction().equals("3002")) {
				list.get(i).setStaisfaction("差");
			} else if (list.get(i).getStaisfaction().equals("3003")) {
				list.get(i).setStaisfaction("一般");
			} else if (list.get(i).getStaisfaction().equals("3004")) {
				list.get(i).setStaisfaction("满意");
			} else if (list.get(i).getStaisfaction().equals("3005")) {
				list.get(i).setStaisfaction("非常满意");
			}
		}
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
