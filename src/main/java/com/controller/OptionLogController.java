package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.OptionLog;
import com.service.OptionLogService;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class OptionLogController {
	@Autowired
   private OptionLogService service;
	 @RequestMapping("/toOptionLogList")
	 public String optionLogList() {
		 return "forward:/WEB-INF/views/sys/optionLogList.jsp";
	 }
    @RequestMapping("/optionLogList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize,String code) throws IOException{
    	Page page = new Page();
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		List<OptionLog> list = service.queryAll((Integer.parseInt(pageNow)-1)*Integer.parseInt(pageSize),Integer.parseInt(pageSize));
		pageCount = service.gettotal(Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    
}