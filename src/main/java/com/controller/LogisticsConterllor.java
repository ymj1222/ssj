package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Logistics;
import com.entity.Pageh;
import com.service.LogisticsService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;

@Controller
public class LogisticsConterllor {
	@Autowired
	private LogisticsService logisticsService;

	@RequestMapping("/findlogisticsAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/sys/logisticsAdd.jsp";
	}
	@RequestMapping("/toLogisticsList")
	public String toLogisticsList() {
		return "forward:/WEB-INF/views/sys/logisticsList.jsp";
	}
	@RequestMapping("/toLogisticsAddr")
	public String toLogisticsAddr(String logisticsNumber,HttpServletRequest request) {
		request.setAttribute("wayBillNo", logisticsNumber);
		return "forward:/WEB-INF/views/sys/logisticsAddr.jsp";
	}
	
	/**
	 * �������
	 * 
	 * @param ter
	 * @param model
	 * @return
	 */
	@RequestMapping("/logisticsAdd")
	public String add(Logistics logistics,Model model,HttpServletRequest request) {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		logistics.setCode(code);
		logistics.setCreateTime(DateUtils.getCurrentDateString());
		logistics.setLastUpdateTime(DateUtils.getCurrentDateString());
		logistics.setCreator(GetNameUtil.getName(request));
		logistics.setLastUpdator(GetNameUtil.getName(request));
		logisticsService.add(logistics);
		
		return "forward:/WEB-INF/views/sys/logisticsList.jsp";
	}
	 @RequestMapping("/logisticsList")
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
			List<Logistics> list = logisticsService.select(ph);
			pageCount = logisticsService.gettotal(ph);
			response.setCharacterEncoding("utf-8");
			page.setList(list);
			page.setPageCount(pageCount);
			page.setPageNow(Integer.parseInt(pageNow));
			String parseJSON = JsonUtils.beanToJson(page);
			response.getWriter().write(parseJSON);
	    }

	
	
	/**
	 * ����codeɾ������
	 * 
	 * @param code
	 */
	@RequestMapping(value = "/logisticsDelete")
	public String delete(Logistics logistics,Model model) {
		logisticsService.delete(logistics.getCode());
		return "forward:/WEB-INF/views/sys/logisticsList.jsp";
	}
	
}
