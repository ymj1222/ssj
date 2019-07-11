package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.City;
import com.service.CityService;

@Controller
@RequestMapping("/city")
public class CityController {
	@Autowired
    CityService service;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<City> list = service.queryAll();
        request.setAttribute("citys", list);
        return "list";
    }
    
   /* @RequestMapping(value="/add")
    public String add(City city){
    	service.addCity(city);
        return "redirect:../city/list";
    }*/
    
  /*  @RequestMapping("/delete")
    public String delete(String code){
    	service.deleteByCode(code);
        return "redirect:../city/list";
    }*/
    
    @RequestMapping("/get")
    public String get(String code,HttpServletRequest request) throws IOException{
        City city = service.queryByCode(code);
        request.setAttribute("city", city);
        return "redirect:../city/list";
    }
    
   /* @RequestMapping("/update")
    public String update(City city){
    	service.updateCity(city);
        System.out.println(city);
        return "redirect:../city/list";
    }*/
}