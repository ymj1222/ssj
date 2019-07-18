package com.controller;

import com.entity.*;
import com.service.*;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertisementConterllor {
	@Autowired
	private TerminalService terminalService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private AdvertisementClickService advertisementClickService;


	@RequestMapping("/findadvertisementList")
	public String findselect() {
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}

	@RequestMapping("/findadvertisementAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/advertisement/advertisementAdd.jsp";
	}
	@RequestMapping("/advertisementPhotoselect")
	protected void getphoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Photo> list = photoService.geturl();
		String parse = JsonUtils.listToJson(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(parse);
	}
	@RequestMapping("/advertisementVideoselect")
	protected void getvideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Video> list = videoService.getUrl();
		String parse = JsonUtils.listToJson(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(parse);
	}
	@ResponseBody
	@RequestMapping("/advertisementTerminalselect")
	protected List<Terminal> getterminal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Terminal> list = terminalService.queryCode();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/advertisementselect")
	public void select(HttpServletRequest request, HttpServletResponse response,String pageNow,String pageSize) throws IOException {
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
        org.springframework.data.domain.Page<Advertisement> list=advertisementService.queryAl(name,page);
        List<Advertisement> l= list.getContent();

        Page pp=new Page();
        pp.setPageCount(list.getTotalPages());
        pp.setList(l);
        pp.setPageNow(Integer.parseInt(pageNow));
        response.setCharacterEncoding("utf-8");

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONObject json =JSONObject.fromObject(pp, jsonConfig);

        String parseJSON = JsonUtils.beanToJson(json);
        response.getWriter().write(parseJSON);
	}
	@ResponseBody
	@RequestMapping("/advertisementqueryl")
	public List<Advertisement> select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Advertisement> list = advertisementService.queryl();
		
		return list;
	}
	@ResponseBody
	@RequestMapping("/advertisementqueryll")
	public Photo query(HttpServletRequest request, HttpServletResponse response,String url) throws IOException {
		Photo p=photoService.queryName(url);
		return p;
	}

	@RequestMapping(value = "/advertisementDelete")
	public String delete(Advertisement advertisement,Model model) {
		advertisementService.delete(advertisement.getCode());
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}
	

	@RequestMapping(value = "/advertisementAdd", method = RequestMethod.POST)
	public String add(String terminalId,Advertisement advertisement,AdvertisementClick advertisementClick,Model model,HttpServletRequest request) {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		Terminal ter = terminalService.updatequery(terminalId);
		advertisement.setTerminalCode(ter.getCode());
		advertisement.setTerminalName(ter.getName());
		advertisement.setCode(code);
		advertisement.setCreateTime(DateUtils.getCurrentDateString());
		advertisement.setUpdateTime(DateUtils.getCurrentDateString());
		advertisement.setCreator(GetNameUtil.getName(request));
		advertisement.setUpdator(GetNameUtil.getName(request));
		advertisement.setTerminal(ter);
		advertisementClick.setAdvertisementCode(code);
		advertisementClick.setCreateTime(DateUtils.getCurrentDateString());
		advertisementClick.setUpdateTime(DateUtils.getCurrentDateString());
		advertisementClick.setCreator(GetNameUtil.getName(request));
		advertisementClick.setUpdator(GetNameUtil.getName(request));
		advertisementClick.setCliclkFrequency(0);
		advertisementClickService.add(advertisementClick);
		advertisement.setAdvertisementClick(advertisementClick);
		advertisementService.add(advertisement);
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}

	@RequestMapping(value = "/advertisementUpdate")
	public String update(Advertisement advertisement, Model model) {
		Advertisement ter = advertisementService.updatequery(advertisement.getCode());
		model.addAttribute("advertisement", ter);
		return "forward:/WEB-INF/views/advertisement/advertisementupdateSave.jsp";
	}

	@RequestMapping(value = "/advertisementUpdateSave")
	public String updateSave(Advertisement advertisement) {
		advertisement.setUpdateTime(DateUtils.getCurrentDateString());
		advertisementService.update(advertisement);
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}

}
