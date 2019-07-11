package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.entity.BrowsingHistory;
import com.service.AccountService;
import com.service.AuthService;
import com.service.BrowsingHistoryService;
import com.service.UsersService;
@Controller
public class TestFilter implements Filter {
	@Autowired
	AuthService a;
	@Autowired
	BrowsingHistoryService b;
	
	@Autowired
	AccountService as;
	@Autowired
	UsersService us;
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
		String a = filterConfig.getInitParameter("account");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		String account = (String) session.getAttribute("account");
		String authCode= (String) session.getAttribute("authCode");
		//shiro      spring security
		//过滤掉登陆页面与登陆SERVELT(包括我们的静态资�?(js.css.jpg.....)都需要进行过滤不�?要权限访�?)
		String url = httpServletRequest.getRequestURI();
		if(url.endsWith("login.jsp") ||url.equals("/ssj/logging")||url.endsWith("login")||url.endsWith("png")||url.endsWith("css")||url.endsWith("js")||url.endsWith("jpg")||url.endsWith("mp4")||url.endsWith("JPG")||url.endsWith("bmp")||url.endsWith("woff")||url.endsWith("woff2")||url.endsWith("ttf")||url.endsWith("jpeg")||url.endsWith("image")||url.endsWith("/ssj/toAccountAdd")||url.endsWith("/ssj/accountAdd")||url.endsWith("/ssj/accountGet")||url.endsWith("qLogin.jsp")||url.endsWith("map"))
		{
			//httpServletResponse.sendRedirect("login.jsp");
			//httpServletResponse.sendRedirect("login.jsp");
			//继续向下执行我们的请�?
			filterChain.doFilter(request, response);
		}
		else
		{
			//表示没有登陆,跳转到登陆页�?
			if(account == null || account.equals("") )
			{
				httpServletResponse.sendRedirect("qLogin.jsp"); 
			}
			else 
			{
				if(!url.endsWith("/ssj/browsingHistoryList")&&!url.endsWith("/ssj/toBrowsingHistoryList")) {
					BrowsingHistory bb=new BrowsingHistory();
					bb.setUsersCode(us.queryByAccount(account));
					Date date = new Date();
					SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
					long bs = System.currentTimeMillis();
					bb.setCode( Long.toString(bs));
					bb.setTime(dateFormat.format(date));
					bb.setUrl(url);
					b.add(bb);
				}
			if(as.queryByAccount(account).getIsAdmin().equals("0")) {
				filterChain.doFilter(request, response);
				return ;
			}	
				String str[]=authCode.split(",");
				for (int i = 0; i < str.length; i++) {
					if(a.queryByCode(str[i])!=null) {
					if(a.queryByCode(str[i]).getUrl().equals(url)) {
						filterChain.doFilter(request, response);
						return ;//下面代码都不执行,代表这个方法结束
					}
					}
				}
				
				httpServletResponse.sendRedirect("qLogin.jsp"); 
			}
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
			
	}

}
