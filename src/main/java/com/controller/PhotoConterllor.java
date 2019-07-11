package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.entity.Photo;
import com.service.PhotoService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;
import com.util.ProUtil;

@Controller
public class PhotoConterllor {
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/findphotoAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/photo/photoAdd.jsp";
	}
	
	
	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findphotoList")
	public String findselect() {
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param ter
	 * @param model��
	 * @return
	 */	
	@RequestMapping(value = "/photoAdd", method = RequestMethod.POST)
	public String springUpload(Photo photo,HttpServletRequest request) throws IllegalStateException, IOException {
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
					String path = ProUtil.getPhotokey() + file.getOriginalFilename();
					file.transferTo(new File(path));
					photo.setUrl("photo/"+file.getOriginalFilename());
				}

			}
		}
		photo.setCode(code);
		photo.setDate(DateUtils.getCurrentDateString());
		photo.setCreator(GetNameUtil.getName(request));
		photo.setUpdator(GetNameUtil.getName(request));
		photo.setCreateTime(DateUtils.getCurrentDateString());
		photo.setUpdateTime(DateUtils.getCurrentDateString());
		photoService.add(photo);
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/photoselect")
	public void select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
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
		pageCount = photoService.gettotal(pageh);
		List<Photo> list = photoService.select(pageh);
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
	@RequestMapping(value = "/photoDelete")
	public String delete(String code) {
		photoService.delete(code);
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}
	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/photoUpdate")
	public String update(Photo photo, Model model) {
		Photo ter = photoService.updatequery(photo.getCode());
		model.addAttribute("photo", ter);
		return "forward:/WEB-INF/views/photo/photoupdateSave.jsp";
	}
	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param ter
	 * @return
	 */
	@RequestMapping(value = "/photoUpdateSave")
	public String updateSave(Photo photo) {
		try {
			String name = photo.getName();
			name = new String(name.getBytes("iso-8859-1"),"utf-8");
			photo.setName(name);
			photo.setUpdateTime(DateUtils.getCurrentDateString());
		photoService.update(photo);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}
}
