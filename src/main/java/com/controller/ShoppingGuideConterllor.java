package com.controller;

import com.entity.Photo;
import com.entity.ServiceRecord;
import com.entity.ShoppingGuide;
import com.service.PhotoService;
import com.service.ServiceRecordService;
import com.service.ShoppingGuideService;
import com.service.UsersService;
import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	 * 找到导购添加页面
 	 * @return
	 */
	@RequestMapping("/findshoppingGuideAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideAdd.jsp";
	}

	/**
	 * 找到导购列表页面
	 * @return
	 */
	@RequestMapping("/findshoppingGuideList")
	public String findselect() {
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * d导购信息添加
	 * @param shoppingGuide
	 * @param model
	 * @param request
	 * @param file
	 * @param file1
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/shoppingGuideAdd", method = RequestMethod.POST)
	public String add(ShoppingGuide shoppingGuide, Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1)
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
			if(file.getSize()>0){
				String filen = file.getOriginalFilename();
				String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
				String destFileName = request.getServletContext().getRealPath("") + "photo/" + fileName;
				System.out.println(destFileName);
				shoppingGuide.setPhoto("photo/" +fileName);
				File destFile = new File(destFileName);
				destFile.getParentFile().mkdirs();
				file.transferTo(destFile);
				Photo photo = new Photo();
				photo.setUrl("photo" + File.separator + fileName);
				photo.setCode(code);
				photo.setDate(DateUtils.getCurrentDateString());
				photo.setCreator(GetNameUtil.getName(request));
				photo.setUpdator(GetNameUtil.getName(request));
				photo.setCreateTime(DateUtils.getCurrentDateString());
				photo.setUpdateTime(DateUtils.getCurrentDateString());
				photoService.add(photo);
			}
			if(file1.getSize()>0){
				String filen = file1.getOriginalFilename();
				String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
				String destFileName = request.getServletContext().getRealPath("") + "photo/"  + fileName;
				System.out.println(destFileName);
				shoppingGuide.setWechatTwoDimensionalCode("photo/"+fileName);
				File destFile = new File(destFileName);
				destFile.getParentFile().mkdirs();
				file1.transferTo(destFile);
				Photo photo = new Photo();
				photo.setUrl("photo" + File.separator + fileName);
				photo.setCode(code);
				photo.setDate(DateUtils.getCurrentDateString());
				photo.setCreator(GetNameUtil.getName(request));
				photo.setUpdator(GetNameUtil.getName(request));
				photo.setCreateTime(DateUtils.getCurrentDateString());
				photo.setUpdateTime(DateUtils.getCurrentDateString());
				photoService.add(photo);
			}
			shoppingGuideService.add(shoppingGuide);
			accountController.addDaoGou(shoppingGuide.getName(),shoppingGuide.getCode(), request);
		}
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * 根据ID删除数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/shoppingGuideDelete")
	public String delete(String id) {
		shoppingGuideService.delete(id);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideList.jsp";
	}

	/**
	 * 带分页的模糊查询
	 * @param model
	 * @param request
	 * @param response
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


	 /* *
	 * 得到要修改的数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/shoppingGuideUpdate")
	public String update(String id, Map<String, Object> map) {
		ShoppingGuide shoppingGuide = shoppingGuideService.updatequery(id);
		map.put("shoppingGuide", shoppingGuide);
		return "forward:/WEB-INF/views/shoppingGuide/shoppingGuideupdateSave.jsp";
	}

	/**
	 * 修改数据
	 * @param shoppingGuide
	 * @param request
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
	 * 忙碌
	 * @param id
	 * @param model
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
	 * 在线
	 * @param id
	 * @param starttime
	 * @param model
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
	 * 离线
	 * @param code
	 */
	@RequestMapping("/updatetypeoff")
	public void updatetypeoff(String code) {
		shoppingGuideService.updatetypeoff(code);
	}

	/**
	 * 导购服务记录添加
	 * @param serviceRecord
	 * @param ShoppingGuideId
	 * @param request
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
