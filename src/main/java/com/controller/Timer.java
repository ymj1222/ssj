package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Addr;
import com.entity.AddrCity;
import com.entity.City;
import com.entity.RouteCity;
import com.service.AddrService;
import com.service.CityService;
import com.service.RouteCityService;
import com.service.WayBillService;
import com.util.JsonUtils;

@Controller
public class Timer {
	@Autowired
	AddrService a;
	@Autowired
	CityService c;
	@Autowired
	RouteCityService rc;
	@Autowired
	WayBillService w;
    
    @RequestMapping("/logisticsAddrUpdate")
    public void time(HttpServletResponse response,String wayBillNo) throws IOException {
		int b=a.queryNumber(wayBillNo);
		List<String>list=new ArrayList<>();
		String str="";
		if(b>=1) {
		int cc=rc.queryNumber(wayBillNo);
		if(cc==b) {
			str="123";
			list.add(str);
		}else {
			Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
			Addr addr1=new Addr();
			addr1.setTime(dateFormat.format(date));
			int c=b+1;
			RouteCity ry=rc.findBySequence(Integer.toString(c), wayBillNo);
			addr1.setCityCode(ry.getCityCode().toString());
			addr1.setW(w.queryByWayBillNo(ry.getwayBillNo()));
			a.addAddr(addr1);
		}
		}
		
		String parseJSON = JsonUtils.listToJson(list); 
		response.getWriter().write(parseJSON);
	}
    @RequestMapping("/logisticsAddrList")
    public void query(HttpServletResponse response,HttpServletRequest request,String wayBillNo) throws IOException {
    	Addr ar=a.findCityCodeByWayBillNo(wayBillNo);
            City ct=c.queryByCode(ar.getCityCode());
            List<AddrCity>list=new ArrayList<>();
            AddrCity ac=new AddrCity();
            int b=a.queryNumber(wayBillNo);
    		if(b>=1) {
    		int cc=rc.queryNumber(wayBillNo);
    		if(cc==b) {
    			ac.setTime(ar.getTime());
    			String name=new String("已到达目的地".getBytes("GBK"),"utf-8");
    			ac.setName(name);
    			ac.setHowLong("0");
    			ac.setDistance("0");
    		}else {
    			ac.setName(ct.getName());
                ac.setTime(ar.getTime());
    			int c=cc-b;
    			ac.setDistance(String.valueOf(c));
    			ac.setHowLong(String.valueOf(c*2));
    		}
    		}
    		 list.add(ac);
            String parseJSON = JsonUtils.listToJson(list);
            response.setCharacterEncoding("utf-8");
    	response.getWriter().write(parseJSON);
    }
   
}