package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.entity.PlatformI;
import com.service.PlatformIService;
import com.util.JsonUtils;
import com.util.ProUtil;

@Controller
public class PlatformIController {
	@Autowired
   private PlatformIService service;
    
    @RequestMapping("/platformIupdate")
    public String get(PlatformI p,HttpServletResponse response,HttpServletRequest request) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				MultipartFile file1 = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = ProUtil.getPhotokey() + file.getOriginalFilename();
					file.transferTo(new File(path));
					p.setLogo("photo/"+file.getOriginalFilename());
				}
				if (file1!= null) {
					String paths = ProUtil.getPhotokey() + file1.getOriginalFilename();
					file1.transferTo(new File(paths));
					p.setQr("photo/"+file1.getOriginalFilename());
			}
		}
			p.setCode("1");
			service.update(p);
    }
		return "forward:/WEB-INF/views/sys/information.jsp";
    }
    
    @RequestMapping("platformIQuery")
    public void query(HttpServletResponse response) throws IOException{
    	String parseJSON = JsonUtils.beanToJson(service.query());
    	response.getWriter().write(parseJSON);    	
    }
    @RequestMapping("toPlatformIList")
    public String toPlatformIList(HttpServletResponse response) throws IOException{
    	return "forward:/WEB-INF/views/sys/information.jsp";
    }
    @RequestMapping("toPlatformIUpdate")
    public String toPlatformIUpdate(HttpServletResponse response) throws IOException{
    	return "forward:/WEB-INF/views/sys/informationUpdate.jsp";
    }
}