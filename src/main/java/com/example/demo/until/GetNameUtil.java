package com.example.demo.until;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetNameUtil {
public static String getName(HttpServletRequest request) {
	HttpSession hs = request.getSession();
	return (String) hs.getAttribute("name");
}
}
