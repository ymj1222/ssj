package com.controller;
    
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Account;
import com.entity.Verification;
import com.service.AccountAuthService;
import com.service.AccountService;
import com.service.AuthService;
import com.service.ShoppingGuideService;
import com.service.VerificationService;
import com.util.MD5Util;
    
@Controller
public class LoginController{
	/**
	 * 
	 */
	@Autowired
	private VerificationService  userService;
	@Autowired
	AccountService ac;
	@Autowired
	AccountAuthService  a;
	@Autowired
	AuthService as;
	@Autowired
	ShoppingGuideService sg;
	@RequestMapping("/logging")
	public String login(HttpServletRequest request, HttpServletResponse response,String account,String password) throws Exception {
		String randomCode = request.getParameter("randomCode");
		String trueRandomCode = (String) request.getSession().getAttribute("rand");
		String hiddenCode = request.getParameter("hiddenCode");
		String fuck=request.getParameter("fuckYou");
		password=MD5Util.md5Encode(password);
		HttpSession hs = request.getSession();
			Verification user  = userService.valid(account,password);
			if(user==null||"".equals(user.getAccount())) {
				request.setAttribute("hiddenCode", "1");
				request.setAttribute("errorMark", "�˻��������������");
				if("fuck".equals(fuck)) {
					return "forward:/qLogin.jsp";
				}else {
					return "forward:/login.jsp";
				}
			}
				if (null == hiddenCode || "".equals(hiddenCode)) {// 等于空的时�??
				//如果用户名正�?,就跳转到成功页面
				if (account.equals(user.getAccount()) && password.equals(user.getPassword())) {// 如果验证码正确，用户名和密码也正确，跳转到欢迎页�?
					hs.setAttribute("account", account);
					String isAdmin=ac.queryByAccount(account).getIsAdmin();
					String authCode="";
					for (int i = 0; i < a.queryByAccount(account).size(); i++) {
							authCode+=a.queryByAccount(account).get(i).getAuthCode()+",";
					}
					hs.setAttribute("authCode",authCode);
					request.setAttribute("account", account);
					if("dd".equals(fuck)&&isAdmin.equals("1")) {
						return "forward:/login.jsp";
					}else if("fuck".equals(fuck)) {
						return "forward:/qindex.jsp";
					}else  {
						Account au=ac.daoGou(ac.queryByAccount(account).getCode());
						if(null ==au||"".equals(au.getAccount())) {
							return "forward:/index.jsp";
						}else {
							sg.getidupdate(ac.queryByAccount(account).getCode());
							return "forward:/index.jsp";
						}
					}
				}
				else
				{
					request.setAttribute("hiddenCode", "1");
					request.setAttribute("errorMark", "�˻��������������");
					if("fuck".equals(fuck)) {
						return "forward:/qLogin.jsp";
					}else {
						return "forward:/login.jsp";
					}
				}
			}
			// 不是第一次登�?,首先�?要进行验证码认证
			else
			{
				//忽略大小�?
				if(randomCode.equalsIgnoreCase(trueRandomCode))
				{
					//如果用户名正�?,就跳转到成功页面
					if (account.equals(user.getAccount()) && password.equals(user.getPassword())) {// 如果验证码正确，用户名和密码也正确，跳转到欢迎页�?
						hs.setAttribute("account", account);
						String isAdmin=ac.queryByAccount(account).getIsAdmin();
						String authCode="";
						for (int i = 0; i < a.queryByAccount(account).size(); i++) {
							authCode+=a.queryByAccount(account).get(i).getAuthCode()+",";
					}
						hs.setAttribute("authCode",authCode);
						request.setAttribute(account, account);
						if("dd".equals(fuck)&&isAdmin.equals("1")) {
							return "forward:/qLogin.jsp";
						}else if("fuck".equals(fuck)) {
							return "forward:/qindex.jsp";
						}else  {
							Account au=ac.daoGou(ac.queryByAccount(account).getCode());
							if(null ==au||"".equals(au.getAccount())) {
								return "forward:/index.jsp";
							}else {
								sg.getidupdate(ac.queryByAccount(account).getCode());
								return "forward:/index.jsp";
							}
						}
					}
					else
					{
						request.setAttribute("hiddenCode", "1");
						request.setAttribute("errorMark", "��֤�����");
						if("fuck".equals(fuck)) {
							return "forward:/qLogin.jsp";
						}else {
							return "forward:/login.jsp";
						}
					}
				}
				else
							{
					request.setAttribute("hiddenCode", "1");
					request.setAttribute("errorMark", "��֤�����");
					if("fuck".equals(fuck)) {
						return "forward:/qLogin.jsp";
					}else {
						return "forward:/login.jsp";
					}
				}
			}
		}
		
		//第一次进�?,不需要进行验证码认证
		
	
	
	
	/**
	 * 如果输入错误各种原因跳会登录页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fuck=request.getParameter("fuckYou");
		request.setAttribute("hiddenCode", 1);
		if("fuck".equals(fuck)) {
			request.getRequestDispatcher("qLogin.jsp").forward(request, response);
			return ;
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	}
    
	public static void main(String[] args) {
		String a ="abcde";
		String b = "aBcdE";
	}
}