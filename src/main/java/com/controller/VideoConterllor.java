package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.entity.Video;
import com.service.VideoService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;
import com.util.ProUtil;

@Controller
public class VideoConterllor {
	@Autowired
	private VideoService videoService;
	
	@RequestMapping("/findvideoUpdate")
	public String findupdate() {
		return "forward:/WEB-INF/views/photo/videoUpdate.jsp";
	}
	
	@RequestMapping("/findvideoAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/photo/videoAdd.jsp";
	}
	
	@RequestMapping("/findvideoList")
	public String findselect() {
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}

	@RequestMapping(value = "/videoAdd", method = RequestMethod.POST)
	public String springUpload(Video photo,HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);

        String filen = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
        String destFileName = request.getServletContext().getRealPath("") + "video" + File.separator + fileName;
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        file.transferTo(destFile);

        photo.setUrl("video" + File.separator + fileName);
		photo.setCode(code);
		photo.setDate(DateUtils.getCurrentDateString());
		photo.setCreator(GetNameUtil.getName(request));
		photo.setUpdator(GetNameUtil.getName(request));
		photo.setCreateTime(DateUtils.getCurrentDateString());
		photo.setUpdateTime(DateUtils.getCurrentDateString());
		videoService.add(photo);
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}
	@ResponseBody
	@RequestMapping("/videoselect")
	public void select(Pageh pageh,Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		pageCount = videoService.gettotal(pageh);
		List<Video> list = videoService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
	}

	@RequestMapping(value = "/videoDelete")
	public String delete(String code) {
		videoService.delete(code);
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}

	
	@RequestMapping(value = "/videoUpdate")
	public String update(Video video, Model model) {
		Video ter = videoService.updatequery(video.getCode());
		model.addAttribute("video", ter);
		return "forward:/WEB-INF/views/photo/videoupdateSave.jsp";
	}
	@RequestMapping(value = "/videoUpdateSave")
	public String updateSave(Video video) {
		video.setUpdateTime(DateUtils.getCurrentDateString());
		videoService.update(video);
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}
}
