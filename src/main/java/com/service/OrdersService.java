package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrdersDao;
import com.entity.Orders;
import com.util.Pageh;

@Service
public class OrdersService {
	@Autowired
	private OrdersDao ordersDao;

	public void add(Orders orders) {
		ordersDao.add(orders);
	}
public void updateIs(String code,int j,String i) {
	ordersDao.updateIs(code,j,i);
}

/**
 * 到货时间
 * @param deliveryTime
 */
public void updateDeliveryTime(String deliveryTime,String logisticsNumber) {
	ordersDao.updateDeliveryTime(deliveryTime,logisticsNumber);
}
/**
 * 发货
 * @param code
 * @param is
 * @param logisticsNumber
 */
public void ordersOut(String code,String is,String logisticsNumber) {
	ordersDao.ordersOut(code, is,logisticsNumber);
}
public void ordersCanel(String code,int i) {
	ordersDao.ordersCanel(code,i);
}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Orders> select(Pageh page) {
		int pageNow = (page.getPageNow()-1)*page.getPageSize();
		page.setPageNow(pageNow);
		List<Orders> list = ordersDao.select(page);
		return list;
	}
	/**
	 * 得到订单未发货的数据
	 * 
	 * @return
	 */
	public List<Orders> orderOutSelect(int now,int pageSize,String is) {
		int pageNow = (now-1)*pageSize;
		List<Orders> list = ordersDao.orderOutSelect(pageNow,pageSize, is);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(int pageSize,String sname,String userCode,String isOutOfStock) {
		int pageCount = 0;
		int rowCount = ordersDao.gettotal(sname,userCode,isOutOfStock);
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Orders updatequery(String code) {
		Orders order = ordersDao.updatequery(code);
		return order;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Orders orders) {
		ordersDao.updateSave(orders);
	}
	public List<Orders> getOrders(String usersCode) {
		List<Orders> list = ordersDao.getOrders(usersCode);
		return list;
	}
}
