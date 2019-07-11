package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductTypeDao;
import com.entity.ProductType;

@Service
public class ProductTypeService {
	@Autowired
	private ProductTypeDao productTypeDao;

	@Transactional
	public void insert(ProductType productType) {
		productTypeDao.insert(productType);
	}

	@Transactional
	public void delete(long code) {
		productTypeDao.delete(code);
	}

	public List<ProductType> selectoption() {
		return productTypeDao.selectoption();
	}

	public List<ProductType> select(String name, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		return productTypeDao.select(name, now, pageSize);
	}

	public Integer gettotal(String name, int pageSize) {
		int pageCount = 0;
		int rowCount = productTypeDao.gettotal(name).intValue();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	public ProductType select(long code) {
		return productTypeDao.select(code);
	}
}
