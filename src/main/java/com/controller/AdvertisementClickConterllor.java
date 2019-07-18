package com.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public void select(HttpServletRequest request, HttpServletResponse response, String pageNow, String pageSize) throws IOException {
        if (null == pageNow || "".equals(pageNow.trim())) {
            pageNow = "1";
        }
        if (null == pageSize || "".equals(pageNow.trim())) {
            pageSize = "3";
        }
        String name=request.getParameter("accm");
        name=name.replaceAll("_","\\\\_");
        int pageNow1=Integer.parseInt(pageNow)-1;
        Pageable page= PageRequest.of(pageNow1,Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, "id"));
        org.springframework.data.domain.Page<AdvertisementClick> list=advertisementClickService.queryAll(name,page);
        List<AdvertisementClick> l= list.getContent();
        Page pp=new Page();
        pp.setPageCount(list.getTotalPages());
        pp.setList(l);
        pp.setPageNow(Integer.parseInt(pageNow));
        response.setCharacterEncoding("utf-8");
        String parseJSON = JsonUtils.beanToJson(pp);
        response.getWriter().write(parseJSON);
    }

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
