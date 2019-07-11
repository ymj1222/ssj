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
import com.entity.Staff;
import com.service.OptionLogService;
import com.service.OrgService;
import com.service.StaffService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class StaffController {
	@Autowired
   private StaffService service;
	@Autowired
	private OptionLogService os;
	@Autowired
	private OrgService oo;
	@RequestMapping("/toStaffList")
	 public String staffList() {
		 return "forward:/WEB-INF/views/sys/staffList.jsp";
	 }
	@RequestMapping("/toStaffAdd")
	public String staffAdd() {
		return "forward:/WEB-INF/views/sys/staffAdd.jsp";
	}
	@RequestMapping("/toStaffUpdate")
	public String staffUpdate(String code,HttpServletRequest request) {
		request.setAttribute("code", code);
		return "forward:/WEB-INF/views/sys/staffUpdate.jsp";
	}
	 @RequestMapping("/staffQuery")
	    public void query(HttpServletRequest request,HttpServletResponse response) throws IOException{
	    	List<Staff> list = service.query();
	    	response.setCharacterEncoding("utf-8");
	    	String parseJSON = JsonUtils.listToJson(list);
	    	response.getWriter().write(parseJSON);
	    }
    @RequestMapping("/staffList")
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
		ph.setObject1(name);
		List<Staff> list = service.queryAll(ph);
		for (int i = 0; i <list.size(); i++) {
			Org org=oo.queryByCode(list.get(i).getOrgCode());
			list.get(i).setOrgCode(org.getName());
		}
		pageCount = service.gettotal(ph);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/staffAdd")
    public String add(Staff staff,HttpServletRequest httpServletRequest){
    	staff.setCreateTime(DateUtils.getCurrentDateTime());
    	staff.setCreator(GetNameUtil.getName(httpServletRequest));
    	staff.setLastUpdateTime(DateUtils.getCurrentDateTime());
    	staff.setLastUpdator(GetNameUtil.getName(httpServletRequest));
    	long bs = System.currentTimeMillis();
    	staff.setCode(Long.toString(bs));
    	os.add(1,httpServletRequest);
    	service.add(staff);
        return "forward:/WEB-INF/views/sys/staffList.jsp";
    }
    
    @RequestMapping("/staffDelete")
    public String delete(String code,HttpServletRequest httpServletRequest){
    	os.add(2,httpServletRequest);
    	service.delete(code);
        return "forward:/WEB-INF/views/sys/staffList.jsp";
    }
    
    @RequestMapping("/staffGet")
    public void get(String code,HttpServletResponse response) throws IOException{
    	Staff staff = service.queryByCode(code);
        String parseJSON=JsonUtils.beanToJson(staff);
        response.getWriter().write(parseJSON);
    }
    
    @RequestMapping("/staffUpdate")
    public String update(Staff staff,HttpServletRequest httpServletRequest){
    	os.add(3,httpServletRequest);
    	service.update(staff);
        return "forward:/WEB-INF/views/sys/staffList.jsp";
    }
    @RequestMapping("/staffEnable")
    public String enable(String state,String code){
    	service.enable(state,code);
    	return "forward:/WEB-INF/views/sys/staffList.jsp";
    }
}