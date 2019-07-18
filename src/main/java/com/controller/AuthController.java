package com.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Auth;
import com.entity.Pageh;
import com.service.AuthService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class AuthController {
	@Autowired
	private AuthService service;

	@RequestMapping("/toAuthList")
	public String authList() {
		return "forward:/WEB-INF/views/sys/authList.jsp";
	}

	@RequestMapping("/toAuthAdd")
	public String authAdd() {
		return "forward:/WEB-INF/views/sys/authAdd.jsp";
	}

	@RequestMapping("/toAuthUpdate")
	public String authUpdate(String code, HttpServletRequest request) {
		request.setAttribute("code", code);
		return "forward:/WEB-INF/views/sys/authUpdate.jsp";
	}

	@ResponseBody
	@RequestMapping("/authList")
	public Page list(HttpServletRequest request, HttpServletResponse response, String pageNow, String pageSize) throws IOException {
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = "3";
		}
		String sname = request.getParameter("sname");
		sname = sname.replaceAll("_", "\\\\_");
		int pageNow1 = Integer.parseInt(pageNow) - 1;
		Pageable page = PageRequest.of(pageNow1, Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, "id"));
		org.springframework.data.domain.Page<Auth> list = service.queryAll(sname, page);
		List<Auth> l = list.getContent();
		Page pp = new Page();
		pp.setPageCount(list.getTotalPages());
		pp.setList(l);
		pp.setPageNow(Integer.parseInt(pageNow));
		response.setCharacterEncoding("utf-8");
		return pp;
	}

	@ResponseBody
    @RequestMapping("/authQuery")
    public List<Auth> query(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	List<Auth> list = service.query();
    	response.setCharacterEncoding("utf-8");
    	return list;
    }
   /* @RequestMapping("/authAdd")
    public String add(Auth auth,HttpServletRequest request){
    	auth.setCreateTime(DateUtils.getCurrentDateTime());
    	auth.setCreator(GetNameUtil.getName(request));
    	auth.setIsDelete("0");
    	auth.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	auth.setLastUpdator(GetNameUtil.getName(request));
    	long bs = System.currentTimeMillis();
    	auth.setCode(Long.toString(bs));
    	service.add(auth);
        return "forward:/WEB-INF/views/sys/authList.jsp";
    }*/
    
    @RequestMapping("/authDelete")
    public String delete(String code,String state){
    	System.out.println(code);
    	service.delete(code,state);
        return "forward:/WEB-INF/views/sys/authList.jsp";
    }
    @ResponseBody
    @RequestMapping("/authGet")
    public Auth get(String code,HttpServletResponse response) throws IOException{
        Auth a = service.queryByCode(code);
        return a;
    }
    
    @RequestMapping("/authUpdate")
    public String update(Auth auth){
    	service.update(auth);
        return "forward:/WEB-INF/views/sys/authList.jsp";
    }
}