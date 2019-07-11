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
	
	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findvideoList")
	public String findselect() {
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param ter
	 * @param model
	 * @return
	 */	
	@RequestMapping(value = "/videoAdd", method = RequestMethod.POST)
	public String springUpload(Video photo,HttpServletRequest request) throws IllegalStateException, IOException {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = ProUtil.getVideokey()+ file.getOriginalFilename();
					file.transferTo(new File(path));
					photo.setUrl("video/"+file.getOriginalFilename());
				}

			}

		}
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
		System.out.println(pageh);
		pageCount = videoService.gettotal(pageh);
		List<Video> list = videoService.select(pageh);
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);
	}

	/**
	 * ����codeɾ������
	 * 
	 * @param code
	 */
	@RequestMapping(value = "/videoDelete")
	public String delete(String code) {
		videoService.delete(code);
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}

	
	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/videoUpdate")
	public String update(Video video, Model model) {
		Video ter = videoService.updatequery(video.getCode());
		model.addAttribute("video", ter);
		return "forward:/WEB-INF/views/photo/videoupdateSave.jsp";
	}
	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param ter
	 * @return
	 */
	@RequestMapping(value = "/videoUpdateSave")
	public String updateSave(Video video) {
		video.setUpdateTime(DateUtils.getCurrentDateString());
		videoService.update(video);
		return "forward:/WEB-INF/views/photo/videoList.jsp";
	}
}
