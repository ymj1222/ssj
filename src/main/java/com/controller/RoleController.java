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
import com.entity.Role;
import com.service.AuthService;
import com.service.OptionLogService;
import com.service.RoleService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class RoleController {
	@Autowired
   private RoleService service;
	@Autowired
	private OptionLogService os;
	@Autowired
	AuthService a;
	@RequestMapping("/toRoleList")
	 public String roleList() {
		 return "forward:/WEB-INF/views/sys/roleList.jsp";
	 }
	@RequestMapping("/toRoleAdd")
	public String roleAdd() {
		return "forward:/WEB-INF/views/sys/roleAdd.jsp";
	}
	 @RequestMapping("/toRoleUpdate")
		public String roleUpdate(String code,HttpServletRequest request) {
			request.setAttribute("code", code);
			return "forward:/WEB-INF/views/sys/roleUpdate.jsp";
		}
	 @RequestMapping("/toRoleFenPei")
	 public String roleFenPei(String roleCode,HttpServletRequest request) {
		 request.setAttribute("roleCode", roleCode);
		 return "forward:/WEB-INF/views/sys/roleFenPei.jsp";
	 }
    @RequestMapping("/roleList")
    public void list(HttpServletRequest request,HttpServletResponse response,String pageNow,String pageSize) throws IOException{
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
		org.springframework.data.domain.Page<Role> list=service.queryAll(sname,page);
		List<Role>l=list.getContent();
		Page pp=new Page();
		pp.setPageCount(list.getTotalPages());
		pp.setList(l);
		pp.setPageNow(Integer.parseInt(pageNow));
		response.setCharacterEncoding("utf-8");
		String parseJSON = JsonUtils.beanToJson(pp);
		response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/roleAdd")
    public String add(Role role,HttpServletRequest request){
    	os.add(7,request);
    	role.setCreateTime(DateUtils.getCurrentDateTime());
    	role.setCreator(GetNameUtil.getName(request));
    	role.setState("0");
    	role.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	role.setLastUpdator(GetNameUtil.getName(request));
    	long bs = System.currentTimeMillis();
    	role.setCode(Long.toString(bs));
    	service.add(role);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
    @ResponseBody
    @RequestMapping("/roleFenPei")
    public List<List<Auth>> roleFenPei(HttpServletResponse response) throws IOException{
    	List<List<Auth>> list = a.queryPcode();
		return list;
    }
    
    @RequestMapping("/roleDelete")
    public String delete(String state,String code,HttpServletRequest request){
    	os.add(8,request);
    	service.delete(state,code);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
    
    @RequestMapping("/roleGet")
    public void get(String code,HttpServletResponse response) throws IOException{
        Role r = service.queryByCode(code);
        String parseJSON=JsonUtils.beanToJson(r);
        response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/roleUpdate")
    public String update(Role role,HttpServletRequest request){
    	os.add(9,request);
    	service.update(role);
        return "forward:/WEB-INF/views/sys/roleList.jsp";
    }
}