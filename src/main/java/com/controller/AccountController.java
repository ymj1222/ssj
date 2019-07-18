package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Account;
import com.entity.AccountRole;
import com.entity.Pageh;
import com.entity.Users;
import com.service.AccountRoleService;
import com.service.AccountService;
import com.service.UsersService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.MD5Util;
import com.util.Page;

@Controller
public class AccountController {
	@Autowired
   private AccountService service;
	@Autowired
	private AccountRoleService ar;
	@Autowired
	private UsersService us;
	 @RequestMapping("/toAccountAdd")
	 public String accountAdd(HttpServletRequest request,String fuck) {
		 request.setAttribute("fuck", fuck);
		 return "forward:/WEB-INF/views/sys/accountAdd.jsp";
	 }
	 @RequestMapping("/toAccountUpdate")
	 public String accountUpdate(HttpServletRequest request,String account) {
		 request.setAttribute("account", account);
		 return "forward:/WEB-INF/views/sys/accountUpdate.jsp";
	 }
	 @RequestMapping("/toAccountAdd1")
	 public String accountAdd1(HttpServletRequest request) {
		 return "forward:/WEB-INF/views/sys/accountAdd1.jsp";
	 }
	 @RequestMapping("/toAccountList")
	 public String accountList(HttpServletRequest request) {
		 return "forward:/WEB-INF/views/sys/accountList.jsp";
	 }
	 @ResponseBody
    @RequestMapping("/accountList")
    public Page list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize) throws IOException{
		 if (null == pageNow || "".equals(pageNow.trim())) {
			 pageNow = "1";
		 }
		 if (null == pageSize || "".equals(pageNow.trim())) {
			 pageSize = "3";
		 }
		 String sname=request.getParameter("sname");
		 sname=sname.replaceAll("_","\\\\_");
	 	int pageNow1=Integer.parseInt(pageNow)-1;
		 Pageable page= PageRequest.of(pageNow1,Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, "id"));
		 org.springframework.data.domain.Page<Account> list=service.queryAll(sname,page);
		 List<Account>l=list.getContent();
		 Page pp=new Page();
		 pp.setPageCount(list.getTotalPages());
		 pp.setList(l);
		 pp.setPageNow(Integer.parseInt(pageNow));
		 response.setCharacterEncoding("utf-8");
		return pp;
    }
    
    @RequestMapping("/accountAdd")
    public String add(String name,String password,String phone,String wechat,String sex,String city,String isAdmin){
    	Account account=new Account();
    	password=MD5Util.md5Encode(password);
    	long bs = System.currentTimeMillis();
    	account.setCode(Long.toString(bs));
    	account.setAccount(phone);
    	account.setPassword(password);
    	account.setName(name);
    	account.setCreateTime(DateUtils.getCurrentDateTime());
    	account.setCreator(name);
    	account.setIsAdmin("1");
    	account.setState("0");
    	account.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	account.setLastUpdator(name);
    	service.add(account);
    	Users user=new Users();
    	user.setPhone(phone);
    	user.setWechat(wechat);
    	user.setSex(sex);
    	user.setCity(city);
    	user.setName(name);
    	user.setAccount(phone);
    	user.setCreateTime(DateUtils.getCurrentDateTime());
    	user.setCreator(name);
    	user.setState("1");
    	user.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	user.setLastUpdator(name);
    	user.setIntegral(0);
    	user.setGoldCoin(0);
    	user.setLevelMark(0);
    	long bs1 = System.currentTimeMillis();
    	user.setCode(Long.toString(bs1));
    	us.add(user);
        return "forward:qLogin.jsp";
    }
    @RequestMapping("/accountAdd1")
    public String add1(String name,String password,String account,String roleCode){
    	Account account1=new Account();
    	password=MD5Util.md5Encode(password);
    	long bs = System.currentTimeMillis();
    	account1.setCode(Long.toString(bs));
    	account1.setAccount(account);
    	account1.setPassword(password);
    	account1.setName(name);
    	account1.setCreateTime(DateUtils.getCurrentDateTime());
    	account1.setCreator(name);
    	account1.setIsAdmin("0");
    	account1.setState("0");
    	account1.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	account1.setLastUpdator(name);
    	service.add(account1);
    	return "forward:index.jsp";
    }
    @RequestMapping("/accountAddDaoGou")
    public String  addDaoGou(String name,String code,HttpServletRequest request){
    	Account a=new Account();
    	a.setName(name);
    	a.setCreateTime(DateUtils.getCurrentDateTime());
    	a.setCreator(GetNameUtil.getName(request));
    	a.setState("0");
    	a.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	a.setLastUpdator(GetNameUtil.getName(request));
    	long bs = System.currentTimeMillis();
    	a.setAccount(Long.toString(bs));
    	a.setPassword(MD5Util.md5Encode(Long.toString(bs)));
    	a.setCode(code);
    	a.setIsAdmin("0");
    	service.add(a);
    	return Long.toString(bs);
    }
    
    @RequestMapping("/accountDelete")
    public String delete(String state,String code){
    	service.delete(state,code);
        return "forward:/WEB-INF/views/sys/accountList.jsp";
    }
    @ResponseBody
    @RequestMapping("/accountGet")
    public Account get(String account,HttpServletResponse response) throws IOException{
        Account a = service.queryByAccount(account);
        return a;
    }
    
    @RequestMapping("/accountUpdate")
    public String update(Account account){
    	Account a=new Account();
    	a.setAccount(account.getAccount());
    	a.setPassword(MD5Util.md5Encode(account.getPassword()));
    	service.update(a);
        return "forward:/WEB-INF/views/sys/accountList.jsp";
    }
}