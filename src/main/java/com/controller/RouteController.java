package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Route;
import com.service.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController {
	@Autowired
    RouteService service;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<Route> list = service.queryAll();
        request.setAttribute("routes", list);
        return "list";
    }
    
    
    @RequestMapping("/delete")
    public String delete(String code){
    	service.deleteByCode(code);
        return "redirect:../route/list";
    }
    
    @RequestMapping("/get")
    public String get(String code,HttpServletRequest request) throws IOException{
    	Route route = service.queryByCode(code);
        request.setAttribute("route", route);
        return "redirect:../route/list";
    }
    
}