package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.AccountService;
import com.service.ShoppingGuideService;

@Controller
public class RemoveSession {
	@Autowired
	   private AccountService as;
	@Autowired
	private ShoppingGuideService sg;
	@RequestMapping("removeSession")
	public void session(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession hs = request.getSession();
		sg.getidupdatedown(as.queryByAccount((String)hs.getAttribute("account")).getCode());
		hs.removeAttribute("account");
		hs.removeAttribute("authCode");
		response.getWriter().write("true");
	}
}
