package com.controller;

import com.entity.ProductBrand;
import com.service.ProductBrandService;
import com.util.CodeUtil;
import com.util.DateUtils;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductBrandConterllor {
	@Autowired
	private ProductBrandService productBrandService;

	@RequestMapping("/ToProductBrandInsert")
	public String ToInsert() {
		return "forward:/WEB-INF/views/product/insertProductBrand.jsp";
	}

	@RequestMapping("/ProductBrandInsert")
	public String Insert(String brand) {

		ProductBrand pb = new ProductBrand();
		CodeUtil code = new CodeUtil();
		pb.setCode(Long.valueOf(code.getCode()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			pb.setCreateTime(date);
			pb.setName(brand);
			productBrandService.insert(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "forward:ToBrandList";
	}

	/**
	 *
	 * @param code
	 * @return �h��Ʒ��
	 */
	@RequestMapping("/ProductBranddelete")
	public String delete(long code) {
		productBrandService.delete(code);
		return "redirect:ToBrandList";
	}

	@ResponseBody
	@RequestMapping(value = "/Brand")
	public List<ProductBrand> specialType(HttpServletResponse response) throws IOException {
		return productBrandService.brand();
	}

	@RequestMapping(value = "/ToBrandList")
	public String ToBrandList() throws IOException {
		return "forward:/WEB-INF/views/product/ProductBrandList.jsp";
	}

	@ResponseBody
	@RequestMapping("/selectBrand")
	public Page both(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		org.springframework.data.domain.Page<ProductBrand> pages=productBrandService.select(name,Integer.parseInt(pageNow), Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(pages.getContent());
		page.setPageCount(pages.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

}
