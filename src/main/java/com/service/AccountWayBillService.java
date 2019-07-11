package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AccountWayBillDao;
import com.dao.AddrDao;
import com.dao.RouteCityDao;
import com.dao.UsersDao;
import com.dao.WayBillDao;
import com.entity.Addr;
import com.entity.Orders;
import com.entity.RouteCity;
import com.entity.Users;
import com.entity.WayBill;
import com.util.DateUtils;
@Service
public class AccountWayBillService {
	@Autowired
AccountWayBillDao dao;
	@Autowired
	UsersDao usersDao;
	@Autowired
	WayBillDao wayBill;
	@Autowired
	AddrDao ad;
	@Autowired
	RouteCityDao rc;
	public void deleteByAccount(String account) {
		dao.deleteByAccount(account);
	} 
	public String addAccountWayBill(Orders o) {
		Users u=usersDao.queryByCode(o.getUsersCode());
		long bs = System.currentTimeMillis();
		WayBill w=new WayBill();
		w.setEndCity(o.getReceivingAddress());
		w.setNumber(o.getAmount());
		w.setrAddr(o.getReceivingAddress());
		w.setrPhone(o.getPhone());
		w.setrName(o.getConsignee());
		w.setWayBillNo(Long.toString(bs));
		w.setStartCity("…Ó€⁄");
		wayBill.addWayBill(w);
		dao.addAccountWayBill(Long.toString(bs), u.getAccount());
		Addr addr=new Addr();
		addr.setCityCode("123");
		addr.setTime(DateUtils.getCurrentDateTime());
		WayBill ww=wayBill.findByWayBillNo(Long.toString(bs));
		addr.setW(ww);
		ad.addAddr(addr);
		RouteCity route=new RouteCity();
		RouteCity route1=new RouteCity();
		RouteCity route2=new RouteCity();
		route.setCityCode(123);
		route.setSequence(1);
		route.setRouteCode("123");
		route.setwayBillNo(Long.toString(bs));
		rc.addRouteCity(route);
		route1.setCityCode(456);
		route1.setSequence(2);
		route1.setRouteCode("123");
		route1.setwayBillNo(Long.toString(bs));
		rc.addRouteCity(route1);
		route2.setCityCode(789);
		route2.setSequence(3);
		route2.setRouteCode("123");
		route2.setwayBillNo(Long.toString(bs));
		rc.addRouteCity(route2);
		return Long.toString(bs);
	}
	public Addr queryByAccount(String account) {
		return dao.findByAccount(account);
	}
	/*public void updateAddr(AccountWayBill aw) {
		dao.updateAccountWayBill(aw);
	}*/
	
}
