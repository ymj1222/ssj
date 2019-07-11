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

import com.entity.WarehousingAndOut;
import com.service.WarehousingAndOutService;
import com.util.Page;

@Controller()
public class WarehousingAndOutConterllor {

	@Autowired
	private WarehousingAndOutService wareService;

	@RequestMapping("/ToOutSelect")
	public String ToOutSelect() {
		return "forward:/WEB-INF/views/warehouse/stockOutList.jsp";
	}

	@ResponseBody
	@RequestMapping("/OutSelect")
	public Page warehouseSelect(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String date = request.getParameter("date");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		pageCount = wareService.gettotal(1, date, Integer.parseInt(pageSize));
		List<WarehousingAndOut> list = wareService.select(1, date, Integer.parseInt(pageNow),
				Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
	@RequestMapping("/Toaddamount")
	public String ToInsert(Integer id,Model model) {
		model.addAttribute("id", id);
		return "forward:/WEB-INF/views/warehouse/insertAmount.jsp";
	}
	@RequestMapping("/addamount")
	public String Insert(Integer id, Integer amount) {
		wareService.addmount(id, amount);
		return "forward:ToWarehouseSelect";
	}
}
