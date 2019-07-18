package com.controller;

import com.entity.Video;
import com.service.VideoService;
import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

        photo.setUrl("video/" + fileName);
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
	public void select(Pageh pageh, Model model, HttpServletRequest request, HttpServletResponse response,String pageNow,String pageSize) throws IOException {
        if (null == pageNow || "".equals(pageNow.trim())) {
            pageNow = "1";
        }
        if (null == pageSize || "".equals(pageNow.trim())) {
            pageSize = "3";
        }
        String sname=request.getParameter("accm");
        sname=sname.replaceAll("_","\\\\_");
        int pageNow1=Integer.parseInt(pageNow)-1;
        Pageable page= PageRequest.of(pageNow1,Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, "id"));
        org.springframework.data.domain.Page<Video> list=videoService.queryAll(sname,page);
        List<Video> l= list.getContent();
        Page pp=new com.util.Page();
        pp.setPageCount(list.getTotalPages());
        pp.setList(l);
        pp.setPageNow(Integer.parseInt(pageNow));
        response.setCharacterEncoding("utf-8");
        String parseJSON = JsonUtils.beanToJson(pp);
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
