package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.AccountAuth;
import com.service.AccountAuthService;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class AccountAuthController {
	@Autowired
   private AccountAuthService service;
    
   /* @RequestMapping("/accountAuthList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize) throws IOException{
    	Page page = new Page();
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		List<AccountAuth> list = service.queryAll(Integer.parseInt(pageNow),Integer.parseInt(pageSize));
		pageCount = service.gettotal(Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }*/
    
    @RequestMapping("/accountAuthAdd")
    public String add(AccountAuth auth){
    	service.add(auth);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
    
    @RequestMapping("/accountAuthDelete")
    public String delete(String account,String authCode){
    	service.delete(account,authCode);
        return "forward:/WEB-INF/views/sys/AccountAuthList.jsp";
    }
    
    @RequestMapping("/accountAuthGet")
    public void get(String account,HttpServletResponse response) throws IOException{
    	List<AccountAuth> a = service.queryByAccount(account);
        String parseJSON=JsonUtils.beanListToJson(a);
        response.getWriter().write(parseJSON);
    }
    
    /*@RequestMapping("/accountAuthUpdate")
    public String update(AccountAuth auth){
    	service.update(auth);
        return "forward:/WEB-INF/views/sys/AccountAuthList.jsp";
    }*/
}