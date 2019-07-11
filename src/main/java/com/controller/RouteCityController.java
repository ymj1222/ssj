package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.RouteCity;
import com.service.RouteCityService;

@Controller
@RequestMapping("/routeCity")
public class RouteCityController {
	@Autowired
    RouteCityService service;
    
    
    @RequestMapping(value="/add")
    public String add(RouteCity route){
    	service.addRouteCity(route);
        return "redirect:../routeCity/list";
    }
    
    @RequestMapping("/delete")
    public String delete(String code){
    	service.deleteByRouteCode(code);
        return "redirect:../route/list";
    }
    
    @RequestMapping("/get")
    public String get(String cityCode,String wayBillNo,HttpServletRequest request) throws IOException{
    	RouteCity route = service.queryByRouteCode(wayBillNo,cityCode);
        request.setAttribute("routeCity", route);
        return "redirect:../route/list";
    }
    
}