package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.WarehouseDao;
import com.dao.WarehousingAndOutDao;
import com.entity.Product;
import com.entity.Warehouse;
import com.entity.WarehousingAndOut;
import com.util.DateUtils;

@Service
public class WarehousingAndOutService {
	@Autowired
	private WarehousingAndOutDao WAODao;
	@Autowired
	private WarehouseDao warehouseDao;

	public List<WarehousingAndOut> select(int type, String date, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		return WAODao.select(date, type, now, pageSize);
	}

	public Integer gettotal(int type, String date, int pageSize) {
		int pageCount = 0;
		int rowCount = WAODao.gettotal(date, type).intValue();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	@Transactional
	public void stock(Integer amount, long productCode, Date createTime, String creator, Long ordersCode) {
		WarehousingAndOut out = new WarehousingAndOut();
		Integer realamount = Integer.valueOf(warehouseDao.amount(productCode).toString());

		out.setOrdersCode(ordersCode);
		out.setCreator(creator);
		out.setCreateTime(createTime);
		out.setType(1);
		out.setProductCode(productCode);
		out.setDate(createTime);
		out.setAmount(amount);
		WAODao.insert(out);
		warehouseDao.stockOut(productCode, Long.valueOf(realamount - amount));

	}

	@Transactional
	public void cancel(Long ordersCode) {
		WarehousingAndOut wao = WAODao.selectorder(ordersCode);
		int realamount = warehouseDao.amount(wao.getProductCode()).intValue();
		warehouseDao.stockOut(wao.getProductCode(), Long.valueOf(realamount + wao.getAmount()));
		WAODao.cancel(ordersCode);
	}

	@Transactional
	public void addmount(int id, int amount) {
		Warehouse warehouse = warehouseDao.addamount(id, amount);
		WarehousingAndOut WAO = new WarehousingAndOut();
		WAO.setAmount(amount);
		WAO.setType(2);
		warehouse.getProduct().getWao().add(WAO);
		WAO.setProduct(warehouse.getProduct());
		WAO.setProductCode(warehouse.getProduct().getCode());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			WAO.setCreateTime(date);
			WAO.setDate(date);
			WAO.setWarehouse(warehouse);
			WAODao.insert(WAO);
			warehouse.getWao().add(WAO);
			warehouse.getProduct().getWao().add(WAO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
