package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.OrdersDao;
import com.entity.Orders;
import com.util.Pageh;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersService {
	@Autowired
	private OrdersDao ordersDao;

	public void add(Orders orders) {
		ordersDao.save(orders);
	}
	@Transactional
	public void updateIs(String code,int j,String i) {
		ordersDao.updateIs(j,i,code);
	}

	/**
	 * 到货时间
	 * @param deliveryTime
	 */
    @Transactional
	public void updateDeliveryTime(String deliveryTime,String logisticsNumber) {
		ordersDao.updateDeliveryTimeByLogisticsNumber(deliveryTime,logisticsNumber);
	}
	/**
	 * 发货
	 * @param code
	 * @param is
	 * @param logisticsNumber
	 */
    @Transactional
	public void ordersOut(String code,String is,String logisticsNumber) {
		ordersDao.ordersOut(is,logisticsNumber,code);
	}

    /**
     * 订单取消
     * @param code
     * @param i
     */
    @Transactional
	public void ordersCanel(String code,int i) {
		ordersDao.ordersCanel(i,code);
	}


	/**
	 * 得到订单未发货的数据
	 *
	 * @return
	 */
	public List<Orders> orderOutSelect(int now,int pageSize,String is) {
		int pageNow = (now-1)*pageSize;
        Pageable page= PageRequest.of(pageNow,pageSize);
        Page<Orders> list = ordersDao.findByIsOutOfStockContaining(is,page);
        List<Orders> l = list.getContent();
		return l;
	}



	/**
	 * 得到要修改的数据
	 *
	 * @param code
	 * @return
	 */
	public Orders updatequery(String code) {
		Orders order = ordersDao.getByCode(code);
		return order;
	}

	/**
	 * 修改数据
	 *
	 * @param orders
	 */
    @Transactional
	public void update(Orders orders) {
		ordersDao.updateSave(orders.getAmount(),orders.getReceivingAddress(),orders.getPhone(),orders.getConsignee(),orders.getCode());
	}

	public List<Orders> getOrders(String usersCode) {
		List<Orders> list = ordersDao.getByUsersCode(usersCode);
		return list;
	}

    public Page<Orders> queryAll(String consignee, String usersCode,String isOutOfStock,Pageable pageable){
        Page<Orders> page = null;
       if(null !=consignee&&""!=consignee&&null !=isOutOfStock&&""!=isOutOfStock) {
            page=ordersDao.findByConsigneeAndUsersCodeAndIsOutOfStockContaining(consignee,usersCode,isOutOfStock,pageable);
        }else if (null !=consignee&&""!=consignee){
            page=ordersDao.findByConsigneeAndUsersCodeContaining(consignee,usersCode,pageable);
        }else if (null !=isOutOfStock&&""!=isOutOfStock){
            page=ordersDao.findByUsersCodeAndIsOutOfStockContaining(usersCode,isOutOfStock,pageable);
        }else if(null !=usersCode&&""!=usersCode){
            page=ordersDao.findByUsersCodeContaining(usersCode,pageable);
        }
        return page;
    }
}
