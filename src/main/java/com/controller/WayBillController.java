package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.WayBill;
import com.service.WayBillService;

@Controller
@RequestMapping("/wayBill")
public class WayBillController {
	@Autowired
    WayBillService service;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<WayBill> list = service.queryAll();
        request.setAttribute("wayBills", list);
        return "list";
    }
    
    @RequestMapping(value="/add")
    public String add(WayBill wayBill){
    	service.addWayBill(wayBill);
        return "redirect:../wayBill/list";
    }
    
    @RequestMapping("/delete")
    public String delete(String wayBillNo){
    	service.deleteByWayBillNo(wayBillNo);
        return "redirect:../wayBill/list";
    }
    
    @RequestMapping("/get")
    public String get(String wayBillNo,HttpServletRequest request) throws IOException{
    	WayBill wayBill = service.queryByWayBillNo(wayBillNo);
        request.setAttribute("wayBill", wayBill);
        return "redirect:../wayBill/list";
    }
    
    @RequestMapping("/update")
    public String update(WayBill wayBill){
    	service.updateWayBill(wayBill);
        System.out.println(wayBill);
        return "redirect:../wayBill/list";
    }
}