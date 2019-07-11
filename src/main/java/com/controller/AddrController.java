package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Addr;
import com.service.AddrService;

@Controller
@RequestMapping("/addr")
public class AddrController {
	@Autowired
   private AddrService dao;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<Addr> list = dao.queryAll();
        request.setAttribute("addrs", list);
        return "list";
    }
    
    @RequestMapping(value="/add")
    public String add(Addr addr){
        dao.addAddr(addr);
        return "redirect:../addr/list";
    }
    
    @RequestMapping("/delete")
    public String delete(String code){
        dao.deleteByCode(code);
        return "redirect:../addr/list";
    }
    
    @RequestMapping("/get")
    public String get(String code,HttpServletRequest request) throws IOException{
        Addr addr = dao.queryByCode(code);
        request.setAttribute("addr", addr);
        return "redirect:../addr/list";
    }
    
   /* @RequestMapping("/update")
    public String update(Addr addr){
        dao.updateAddr(addr);
        return "redirect:../addr/list";
    }*/
}