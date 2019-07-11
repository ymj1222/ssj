package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.AccountDao;
import com.service.AccountService;

@Controller
public class NameController {
	@Autowired
	AccountDao ad;
	@Autowired
	AccountService as;
@RequestMapping("getAccountName")
public void getName(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
	HttpSession hs = request.getSession();
	String ac=(String) hs.getAttribute("account");
	String name="";
	if(null!=ac) {
		if(as.queryByAccount(ac).getIsAdmin()=="0") {
			name="����";
		}
		else {		
			name=ad.queryByAccount(ac).getName();
		}
		hs.setAttribute("name", name);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"name\":\""+name+"\"}");
		
	}
}
}
