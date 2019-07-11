package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Advertisement;
import com.entity.AdvertisementClick;
import com.entity.Photo;
import com.entity.Terminal;
import com.entity.Video;
import com.service.AdvertisementClickService;
import com.service.AdvertisementService;
import com.service.PhotoService;
import com.service.TerminalService;
import com.service.VideoService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;

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


	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
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
	public Page select(Pageh pageh,Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		pageh.setPageNow((Integer.parseInt(pageNow)-1)*Integer.parseInt(pageSize));
		pageh.setPageSize(Integer.parseInt(pageSize));
		name=name.replaceAll("_","\\\\_");
		pageh.setObject1(name);
		pageCount = advertisementService.gettotal(pageh);
		List<Advertisement> list = advertisementService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
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
	/**
	 * ����codeɾ������
	 * 
	 * @param code
	 */
	@RequestMapping(value = "/advertisementDelete")
	public String delete(Advertisement advertisement,Model model) {
		advertisementService.delete(advertisement.getCode());
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}
	
	/**
	 * �������
	 * 
	 * @param ter
	 * 
	 * @param model
	 * @return
	 */
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
	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/advertisementUpdate")
	public String update(Advertisement advertisement, Model model) {
		Advertisement ter = advertisementService.updatequery(advertisement.getCode());
		model.addAttribute("advertisement", ter);
		return "forward:/WEB-INF/views/advertisement/advertisementupdateSave.jsp";
	}
	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param ter
	 * @return
	 */
	@RequestMapping(value = "/advertisementUpdateSave")
	public String updateSave(Advertisement advertisement) {
		advertisement.setUpdateTime(DateUtils.getCurrentDateString());
		advertisementService.update(advertisement);
		return "forward:/WEB-INF/views/advertisement/advertisementList.jsp";
	}

}
