package com.controller;

import com.entity.ProductEvaluation;
import com.service.ProductEvaluationService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller()
public class ProductEvaluationConterllor {
	@Autowired
	private ProductEvaluationService productEvaluationService;

	@RequestMapping("/ToProductEvaluationInsert")
	public String ToProductEvaluationInsert() {
		return "forward:/WEB-INF/views/warehouse/productEvaluationInsert.jsp";
	}

	@RequestMapping("/ProductEvaluationInsert")
	public String ProductEvaluationInsert(ProductEvaluation ProductEvaluation) {
		productEvaluationService.insert(ProductEvaluation);
		return "forward:qindex.jsp";
	}

	@RequestMapping("/ToProductEvaluationSelect")
	public String ToOutSelect() {
		return "forward:/WEB-INF/views/product/productEvaluationList.jsp";
	}

	@ResponseBody
	@RequestMapping("/ProductEvaluationSelect")
	public Page ProductEvaluationSelect(HttpServletRequest request, HttpServletResponse response){
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String code = request.getParameter("code");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		if(null == code|| "".equals(code.trim())){
			code="0";
		}
		org.springframework.data.domain.Page<ProductEvaluation>pages=productEvaluationService.select(Long.valueOf(code),Integer.parseInt(pageNow),Integer.parseInt(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(pages.getContent());
		page.setPageCount(pages.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}
}
