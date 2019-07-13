package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String get(PlatformI p,HttpServletResponse response,HttpServletRequest request,@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1) throws IOException{
				if (file.getSize() >0) {
					String filen = file.getOriginalFilename();
					String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
					String destFileName = request.getServletContext().getRealPath("") + "photo/" + fileName;
					File destFile = new File(destFileName);
					destFile.getParentFile().mkdirs();
					file.transferTo(destFile);
					p.setLogo("photo" + File.separator + fileName);
				}
				if (file1.getSize()>0) {
					String filen = file1.getOriginalFilename();
					String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
					String destFileName = request.getServletContext().getRealPath("") + "photo/" +fileName;
					File destFile = new File(destFileName);
					destFile.getParentFile().mkdirs();
					file1.transferTo(destFile);
					p.setQr("photo" + File.separator + fileName);
			}
			p.setCode("1");
			service.update(p);
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