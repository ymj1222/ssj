package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.dao.WarehouseDao;
import com.entity.Warehouse;

@Service
public class WarehouseService {
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ProductDao productDao;

	public List<Warehouse> selectwarehouse(String name, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		List<Warehouse> list = warehouseDao.selectwarehouse(name, now, pageSize);
		for (int i = 0; i <list.size(); i++) {
			list.get(i).setProductName(list.get(i).getProduct().getName());
		}
		return list;
	}

	public Integer gettotal(String name, int pageSize) {
		int pageCount = 0;
		int rowCount = warehouseDao.gettotal(name).intValue();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}
}
