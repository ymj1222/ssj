package com.controller;

import com.entity.Warehouse;
import com.service.WarehouseService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller()
public class WarehouseConterllor {

	@Autowired
	private WarehouseService warehouseService;

	@RequestMapping("/ToWarehouseSelect")
	public String ToWarehouseSelect() {
		return "forward:/WEB-INF/views/warehouse/warehouseList.jsp";
	}

	@ResponseBody
	@RequestMapping("/WarehouseSelect")
	public Page warehouseSelect(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		org.springframework.data.domain.Page<Warehouse>pages=warehouseService.selectwarehouse(name,Integer.valueOf(pageNow),Integer.valueOf(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(pages.getContent());
		page.setPageCount(pages.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
