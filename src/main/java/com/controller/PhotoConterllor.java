package com.controller;

import com.entity.Photo;
import com.service.PhotoService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class PhotoConterllor {
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/findphotoAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/photo/photoAdd.jsp";
	}

	@RequestMapping("/findphotoList")
	public String findselect() {
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}

	@RequestMapping(value = "/photoAdd", method = RequestMethod.POST)
	public String springUpload(Photo photo,HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);

        String filen = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
        String destFileName = request.getServletContext().getRealPath("") + "photo/"+ fileName;
        System.out.println(destFileName);
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        file.transferTo(destFile);

        photo.setUrl("photo/"+ fileName);
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
	public void select(Model model, HttpServletRequest request, HttpServletResponse response,String pageNow,String pageSize) throws IOException {
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
        org.springframework.data.domain.Page<Photo> list=photoService.queryAll(sname,page);
        List<Photo> l= list.getContent();
        Page pp=new Page();
        pp.setPageCount(list.getTotalPages());
        pp.setList(l);
        pp.setPageNow(Integer.parseInt(pageNow));
        response.setCharacterEncoding("utf-8");
        String parseJSON = JsonUtils.beanToJson(pp);
        response.getWriter().write(parseJSON);
	}
	@RequestMapping(value = "/photoDelete")
	public String delete(String code) {
		photoService.delete(code);
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}
	@RequestMapping(value = "/photoUpdate")
	public String update(Photo photo, Model model) {
		Photo ter = photoService.updatequery(photo.getCode());
		model.addAttribute("photo", ter);
		return "forward:/WEB-INF/views/photo/photoupdateSave.jsp";
	}
	@RequestMapping(value = "/photoUpdateSave")
	public String updateSave(Photo photo) {
			String name = photo.getName();
			photo.setName(name);
			photo.setUpdateTime(DateUtils.getCurrentDateString());
		photoService.update(photo);
		return "forward:/WEB-INF/views/photo/photoList.jsp";
	}
}
