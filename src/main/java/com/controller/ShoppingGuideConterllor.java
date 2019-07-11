package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.entity.Photo;
import com.entity.ServiceRecord;
import com.entity.ShoppingGuide;
import com.service.PhotoService;
import com.service.ServiceRecordService;
import com.service.ShoppingGuideService;
import com.service.UsersService;

@Controller
public class ShoppingGuideConterllor {
	@Autowired
	private PhotoService photoService;

	@Autowired
	private ShoppingGuideService shoppingGuideService;
	@Autowired
	private ServiceRecordService serviceRecordService;

	@Autowired
	private AccountController accountController;

	@Autowired
	private UsersService usersService;

	/**
	 * ?????????
	 * 
	 * @return
	 */
	@RequestMapping("/findshoppingGuideAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideAdd.jsp";
	}

	/**
	 * ?????????
	 * 
	 * @return
	 */
	@RequestMapping("/findshoppingGuideList")
	public String findselect() {
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * ???????
	 * 
	 * @param shoppingGuide
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/shoppingGuideAdd", method = RequestMethod.POST)
	public String add(ShoppingGuide shoppingGuide, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (shoppingGuide.getName() != null) {
			String name = GetNameUtil.getName(request);
			long bs = System.currentTimeMillis();
			String code = Long.toString(bs);
			shoppingGuide.setCode(code);
			shoppingGuide.setCreator(name);
			shoppingGuide.setUpdator(name);
			shoppingGuide.setCreateTime(DateUtils.getCurrentDateTime());
			shoppingGuide.setUpdateTime(DateUtils.getCurrentDateTime());
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<?> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					MultipartFile file1 = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String path = ProUtil.getPhotokey() + file.getOriginalFilename();
						file.transferTo(new File(path));
						shoppingGuide.setPhoto("photo/" + file.getOriginalFilename());
						Photo photo = new Photo();
						photo.setCode(code);
						photo.setName(shoppingGuide.getName());
						photo.setUrl("photo/" + file.getOriginalFilename());
						photo.setCreateTime(DateUtils.getCurrentDateString());
						photo.setDate(DateUtils.getCurrentDateString());
						photo.setCreator(name);
						photo.setUpdator(name);
						photo.setUpdateTime(DateUtils.getCurrentDateString());
						photoService.add(photo);
					}
					if (file1 != null) {
						String paths = ProUtil.getPhotokey() + file1.getOriginalFilename();
						file1.transferTo(new File(paths));
						shoppingGuide.setWechatTwoDimensionalCode("photo/" + file1.getOriginalFilename());
						Photo photo = new Photo();
						long bs1 = System.currentTimeMillis();
						String code1 = Long.toString(bs1);
						photo.setCode(code1);
						photo.setName(shoppingGuide.getName() + "?????");
						photo.setUrl("photo/" + file1.getOriginalFilename());
						photo.setDate(DateUtils.getCurrentDateString());
						photo.setCreateTime(DateUtils.getCurrentDateString());
						photo.setCreator(name);
						photo.setUpdator(name);
						photo.setUpdateTime(DateUtils.getCurrentDateString());
						photoService.add(photo);
					}
				}
			}

			shoppingGuideService.add(shoppingGuide);
			accountController.addDaoGou(shoppingGuide.getName(),shoppingGuide.getCode(), request);
		}
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * ????id???????
	 * 
	 * @param id
	 */
	@RequestMapping("/shoppingGuideDelete")
	public String delete(String id) {
		shoppingGuideService.delete(id);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * ???????????
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/shoppingGuideselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		pageh.setPageNow((Integer.parseInt(pageNow) - 1) * Integer.parseInt(pageSize));
		pageh.setPageSize(Integer.parseInt(pageSize));
//		name=name.replaceAll("_", "\\\\_");
		pageh.setObject1(SearchTool.decodeSpecialCharsWhenLikeUseSlash(name));
		int pageCount = 0;
		pageCount = shoppingGuideService.gettotal(pageh);
		List<ShoppingGuide> list = shoppingGuideService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	/**
	 * ????????????
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/shoppingGuideUpdate")
	public String update(String id, Map<String, Object> map) {
		ShoppingGuide shoppingGuide = shoppingGuideService.updatequery(id);
		map.put("shoppingGuide", shoppingGuide);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideupdateSave.jsp";
	}

	/**
	 * ???????
	 * 
	 * @param id
	 * @param shoppingGuide
	 * @return
	 */
	@RequestMapping("/shoppingGuideUpdateSave")
	public String updateSave(ShoppingGuide shoppingGuide, HttpServletRequest request) {
		shoppingGuide.setUpdateTime(DateUtils.getCurrentDateTime());
		String name = GetNameUtil.getName(request);
		shoppingGuide.setUpdator(name);
		shoppingGuideService.update(shoppingGuide);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatetypebusy")
	public String updatetypebusy(String id, Model model) {
		shoppingGuideService.updatetypebusy(id);
		model.addAttribute("id", id);
		String starttime = DateUtils.getCurrentDateTime();
		model.addAttribute("starttime", starttime);
		return "forward:/WEB-INF/views/shoppingGuide/shoppGuideservice.jsp";
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatetypeon")
	public String updatetypeon(String id, String starttime, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("starttime", starttime);
		String endtime = DateUtils.getCurrentDateTime();
		model.addAttribute("endtime", endtime);
		shoppingGuideService.updatetypeon(id);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideevaluate.jsp";
	}

	/**
	 * ???????????????????
	 * 
	 * @return
	 */
	@RequestMapping("/updatetypeoff")
	public void updatetypeoff(String code) {
		shoppingGuideService.updatetypeoff(code);
	}

	/**
	 * ?????????????????????????????????
	 * 
	 * @param serviceRecord
	 * @return
	 */
	@RequestMapping("/updatetypefuck")
	public String serviceadd(ServiceRecord serviceRecord, String ShoppingGuideId, HttpServletRequest request) {

		String name = GetNameUtil.getName(request);
		HttpSession hs = request.getSession();
		String account = (String) hs.getAttribute("account");
		String userCode = usersService.queryByAccount(account);
		long time = DateUtils.getDistanceTimes(serviceRecord.getStartTime(), serviceRecord.getEndTime());
		serviceRecord.setCreateTime(DateUtils.getCurrentDateTime());
		serviceRecord.setUpdateTime(DateUtils.getCurrentDateTime());
		serviceRecord.setUserName(name);
		serviceRecord.setUserCode(userCode);
		serviceRecord.setTime(Long.toString(time));
		serviceRecord.setCreator(name);
		serviceRecord.setUpdator(name);
		serviceRecordService.add(serviceRecord, ShoppingGuideId);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}
}
