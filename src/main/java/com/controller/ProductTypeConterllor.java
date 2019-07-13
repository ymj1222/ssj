package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ProductType;
import com.util.CodeUtil;
import com.util.DateUtils;
import com.util.JsonUtils;
import com.util.Page;

@Controller()
public class ProductTypeConterllor {
	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping("/ToProductTypeInsert")
	public String ToInsert() {
		return "forward:/WEB-INF/views/product/insertProductType.jsp";
	}

	@RequestMapping("/ProductTypeInsert")
	public String Insert(String type, String level) {
		ProductType pt = new ProductType();
		pt.setName(type);
		pt.setLevel(Byte.valueOf(level));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			pt.setCreateTime(date);
			CodeUtil code = new CodeUtil();
			pt.setCode(Long.valueOf(code.getCode()));
			productTypeService.insert(pt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:ToProductTypeselect";
	}

	/**
	 *
	 * @param code
	 * @return �h��e
	 */
	@RequestMapping("/ProductTypedelete")
	public String delete(long code) {
		productTypeService.delete(code);

		return "forward:ToProductTypeselect";
	}

	@ResponseBody
	@RequestMapping("/ToProductoption")
	public List<ProductType> ToProductInsert(HttpServletRequest request, HttpServletResponse response) {
		List<ProductType> list = productTypeService.selectoption();
		response.setCharacterEncoding("utf-8");
		return list;
	}

	@RequestMapping("/ToProductTypeselect")
	public String ToProductTypeselect() {
		return "forward:/WEB-INF/views/product/productTypeselect.jsp";
	}
@ResponseBody
	@RequestMapping("/ProductTypeselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		pageCount = productTypeService.gettotal(name.replaceAll("_", "\\\\_"), Integer.parseInt(pageSize));
		List<ProductType> list = productTypeService.select(name.replaceAll("_", "\\\\_"), Integer.parseInt(pageNow), Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
