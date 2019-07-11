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

import com.entity.Advertisement;
import com.entity.AdvertisementClick;
import com.entity.Photo;
import com.entity.Product;
import com.service.AdvertisementClickService;
import com.service.AdvertisementService;
import com.service.PhotoService;
import com.service.ProductService;
import com.util.DateUtils;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;

@Controller
public class AdvertisementClickConterllor {
	@Autowired
	private ProductService productService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private AdvertisementClickService advertisementClickService;

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findadvertisementClickList")
	public String findselect() {
		return "forward:/WEB-INF/views/advertisement/advertisementClickList.jsp";
	}
	

	@ResponseBody
	@RequestMapping("/advertisementClickselect")
	public void select(Pageh pageh, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");

		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		String name = request.getParameter("accm");
		pageh.setPageNow((Integer.parseInt(pageNow) - 1) * Integer.parseInt(pageSize));
		pageh.setPageSize(Integer.parseInt(pageSize));
		name=name.replaceAll("_","\\\\_");
		pageh.setObject1(name);
		pageCount = advertisementClickService.gettotal(pageh);
		List<AdvertisementClick> list = advertisementClickService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
	}
	/**
	 * �޸�����
	 * 
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/advertisementClickUpdate")
	public String update(Advertisement advertisement,AdvertisementClick advertisementClick, Model model) {
		Advertisement adver = advertisementService.getquery(advertisement.getPhoto());
		AdvertisementClick ter = advertisementClickService.updatequery(adver.getCode());
		Photo photoe = photoService.getproductCode(advertisement.getPhoto());
		String code = photoe.getProductCode();
		Integer fil = ter.getCliclkFrequency();
		int fi = fil + 1;
		advertisementClick.setAdvertisementCode(ter.getAdvertisementCode());
		advertisementClick.setCliclkFrequency(fi);
		advertisementClick.setUpdateTime(DateUtils.getCurrentDateString());
		advertisementClickService.update(advertisementClick);
		Product product = productService.updatequery(Long.valueOf(code));
		model.addAttribute("name", product.getName());
		model.addAttribute("price", product.getPrice());
		model.addAttribute("amount", product.getAmount());
		model.addAttribute("sell", product.getSellValue());
		model.addAttribute("market", product.getMarketValue());
		model.addAttribute("size", product.getSize());
		model.addAttribute("color", product.getColor());
		model.addAttribute("code", product.getCode());
		model.addAttribute("induction", product.getInduction());
		List<Photo> photos = photoService.getphotoUrl(code);
		for (int i = 0; i < photos.size(); i++) {
			String photo = "photo";
			Integer a = i + 1;
			String b = a.toString();
			photo = photo + b;
			model.addAttribute(photo, photos.get(i).getUrl());
		}
		return "forward:qdetails.jsp";
	} 
}
