package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.RoleAuth;
import com.service.RoleAuthService;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class RoleAuthController {
	@Autowired
   private RoleAuthService service;
    
    @RequestMapping("/roleAuthList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize) throws IOException{
    	Page page = new Page();
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		List<RoleAuth> list = service.queryAll(Integer.parseInt(pageNow),Integer.parseInt(pageSize));
		pageCount = service.gettotal(Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/roleAuthAdd")
    public String add(RoleAuth roleAuth){
    	service.add(roleAuth);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
    
    @RequestMapping("/roleAuthDelete")
    public String delete(String roleCode,String authCode){
    	service.delete(roleCode,authCode);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
    
    @RequestMapping("/roleAuthGet")
    public void get(String roleCode,HttpServletResponse response) throws IOException{
    	List<RoleAuth> a = service.queryByRoleCode(roleCode);
        String parseJSON=JsonUtils.beanListToJson(a);
        response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/roleAuthUpdate")
    public String update(RoleAuth roleAuth){
    	service.update(roleAuth);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
}