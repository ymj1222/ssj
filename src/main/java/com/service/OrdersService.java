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
 * ����ʱ��
 * @param deliveryTime
 */
public void updateDeliveryTime(String deliveryTime,String logisticsNumber) {
	ordersDao.updateDeliveryTime(deliveryTime,logisticsNumber);
}
/**
 * ����
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
	 * �õ���������
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
	 * �õ�����δ����������
	 * 
	 * @return
	 */
	public List<Orders> orderOutSelect(int now,int pageSize,String is) {
		int pageNow = (now-1)*pageSize;
		List<Orders> list = ordersDao.orderOutSelect(pageNow,pageSize, is);
		return list;
	}

	/**
	 * �õ�������ҳ��
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
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Orders updatequery(String code) {
		Orders order = ordersDao.updatequery(code);
		return order;
	}

	/**
	 * �޸�����
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
