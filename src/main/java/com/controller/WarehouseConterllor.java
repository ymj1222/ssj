package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Warehouse;
import com.service.WarehouseService;
import com.util.JsonUtils;
import com.util.Page;

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
		int pageCount = 0;
		pageCount = warehouseService.gettotal(name.replaceAll("_", "\\\\_"), Integer.valueOf(pageSize));
		List<Warehouse> list = warehouseService.selectwarehouse(name.replaceAll("_", "\\\\_"), Integer.valueOf(pageNow),
				Integer.valueOf(pageSize));

		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
