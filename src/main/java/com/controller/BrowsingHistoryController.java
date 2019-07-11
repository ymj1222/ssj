package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.BrowsingHistory;
import com.service.BrowsingHistoryService;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class BrowsingHistoryController {
	@Autowired
   private BrowsingHistoryService service;
    
    @RequestMapping("/browsingHistoryList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize,String code) throws IOException{
    	Page page = new Page();
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		List<BrowsingHistory> list = service.queryAll((Integer.parseInt(pageNow)-1)*Integer.parseInt(pageSize),Integer.parseInt(pageSize),code);
		pageCount = service.gettotal(Integer.parseInt(pageSize),code);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    @RequestMapping("toBrowsingHistoryList")
    public String toList(String code,HttpServletRequest request) {
    	request.setAttribute("code", code);
    	 return "forward:/WEB-INF/views/sys/browsingHistoryList.jsp";
    }
    
}