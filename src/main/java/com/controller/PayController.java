package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Pay;
import com.service.PayService;
import com.util.JsonUtils;

@Controller
public class PayController {
	@Autowired
	PayService p;
	@RequestMapping("/toPayList")
	 public String payList() {
		 return "forward:/WEB-INF/views/sys/pay.jsp";
	 }
	
	@RequestMapping("/toAddrList")
	public String addrList() {
		return "forward:/WEB-INF/views/sys/addr.jsp";
	}
	
	@RequestMapping("/payList")
	public void list(HttpServletResponse response) throws IOException {
		String parseJSON = JsonUtils.listToJson(p.queryAll());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(parseJSON);
	}
	
	@RequestMapping("/payUpdate")
	public void update(HttpServletResponse response,String name) throws IOException {
		String str[]=name.split(",");
		p.update(str[1]);
		p.update1(str[1], str[0]);
		response.getWriter().write("{\"name\":\"zs\"}");
	}
	
}