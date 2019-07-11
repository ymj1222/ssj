package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Org;
import com.entity.Pageh;
import com.service.OptionLogService;
import com.service.OrgService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class OrgController {
	@Autowired
   private OrgService service;
	@Autowired
	private OptionLogService os;
	 @RequestMapping("/toOrgList")
	 public String orgList() {
		 return "forward:/WEB-INF/views/sys/orgList.jsp";
	 }
	 @RequestMapping("/toOrgAdd")
	 public String orgAdd() {
		 return "forward:/WEB-INF/views/sys/orgAdd.jsp";
	 }
		@RequestMapping("/toOrgUpdate")
		public String orgUpdate(String code,HttpServletRequest request) {
			request.setAttribute("code", code);
			return "forward:/WEB-INF/views/sys/orgUpdate.jsp";
		}
	 @RequestMapping("/toDetailsList")
		public String detailsList(String code,HttpServletRequest request) {
			request.setAttribute("code", code);
			return "forward:/WEB-INF/views/sys/staffDetails.jsp";
		}
    @RequestMapping("/orgList")
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
		List<Org> list = service.queryAll(ph);
		pageCount = service.gettotal(ph);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    @RequestMapping("/orgQuery")
    public void query(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	List<Org> list = service.query();
    	response.setCharacterEncoding("utf-8");
    	String parseJSON = JsonUtils.listToJson(list);
    	response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/orgAdd")
    public String add(Org org,HttpServletRequest request){
    	org.setCreateTime(DateUtils.getCurrentDateTime());
    	org.setCreator(GetNameUtil.getName(request));
    	org.setState("0");
    	org.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	org.setLastUpdator(GetNameUtil.getName(request));
    	long bs = System.currentTimeMillis();
    	org.setCode(Long.toString(bs));
    	os.add(4,request);
    	service.add(org);
        return "forward:/WEB-INF/views/sys/orgList.jsp";
    }
    
    @RequestMapping("/orgDelete")
    public String delete(String code,HttpServletRequest request){
    	os.add(5,request);
    	service.delete(code);
        return "forward:/WEB-INF/views/sys/orgList.jsp";
    }
   
    @RequestMapping("/orgGet")
    public void get(String code,HttpServletResponse response) throws IOException{
        Org a = service.queryByCode(code);
        String parseJSON=JsonUtils.beanToJson(a);
        response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/orgUpdate")
    public String update(Org org,HttpServletRequest request){
    	os.add(6,request);
    	service.update(org);
        return "forward:/WEB-INF/views/sys/orgList.jsp";
    }
    @RequestMapping("/orgEnable")
    public String enable(String state,String code){
    	service.enable(state,code);
    	return "forward:/WEB-INF/views/sys/orgList.jsp";
    }
}