package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Account;
import com.entity.Pageh;
import com.entity.Users;
import com.service.AccountService;
import com.service.UsersService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.MD5Util;
import com.util.Page;

@Controller
public class UsersController {
	@Autowired
   private UsersService service;
	@Autowired
	private AccountService as;
	 @RequestMapping("/toUsersList")
	 public String usersList() {
		 return "forward:/WEB-INF/views/sys/usersList.jsp";
	 }
	 @RequestMapping("/toUsersAdd")
	 public String usersAdd() {
		 return "forward:/WEB-INF/views/sys/usersAdd.jsp";
	 }
	 @RequestMapping("/toOrdersHistoryList")
	 public String ordersList(String usersCode,HttpServletRequest request) {
		 request.setAttribute("usersCode", usersCode);
		 return "forward:/WEB-INF/views/sys/ordersList.jsp";
	 }
    @RequestMapping("/usersList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize) throws IOException{
    	Page page = new Page();
    	Pageh ph=new Pageh();
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		String name=request.getParameter("sname");
		ph.setPageNow((Integer.parseInt(pageNow)-1)*Integer.parseInt(pageSize));
		ph.setPageSize(Integer.parseInt(pageSize));
		name=name.replaceAll("_","\\\\_");
		ph.setObject1(name);
		List<Users> list = service.queryAll(ph);
		pageCount = service.gettotal(ph);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/usersAdd")
    public String add(Users user,String password,HttpServletRequest request){
    	user.setCreateTime(DateUtils.getCurrentDateTime());
    	user.setCreator(GetNameUtil.getName(request));
    	user.setState("1");
    	user.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	user.setLastUpdator(GetNameUtil.getName(request));
    	user.setIntegral(0);
    	user.setGoldCoin(0);
    	user.setLevelMark(0);
    	long bs = System.currentTimeMillis();
    	user.setCode(Long.toString(bs));
    	service.add(user);
    	long bs1 = System.currentTimeMillis();
    	Account a=new Account();
    	a.setAccount(user.getAccount());
    	a.setCode(Long.toString(bs1));
    	a.setIsAdmin("1");
    	a.setName(user.getName());
    	a.setState("0");
    	a.setPassword(MD5Util.md5Encode(password));
    	System.out.println(Long.toString(bs));
    	a.setCreateTime(DateUtils.getCurrentDateTime());
    	a.setCreator(GetNameUtil.getName(request));
    	a.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	a.setLastUpdator(GetNameUtil.getName(request));
    	as.add(a);
        return "forward:/WEB-INF/views/sys/usersList.jsp";
    }
    
    @RequestMapping("/usersDelete")
    public String delete(String state,String code){
    	service.delete(state,code);
    	as.delete(state,code);
        return "forward:/WEB-INF/views/sys/usersList.jsp";
    }
    
    @RequestMapping("/usersGet")
    public void get(String code,HttpServletResponse response) throws IOException{
    	Users a = service.queryByCode(code);
        String parseJSON=JsonUtils.beanToJson(a);
        response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/usersUpdate")
    public String update(Users org){
    	service.update(org);
        return "forward:/WEB-INF/views/sys/usersList.jsp";
    }
}