package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductBrandDao;
import com.entity.ProductBrand;

@Service
public class ProductBrandService {
	@Autowired
	private ProductBrandDao productBrandDao;

	@Transactional
	public void insert(ProductBrand productBrand) {
		productBrandDao.insert(productBrand);
	}

	@Transactional
	public void delete(long code) {
		productBrandDao.delete(code);
	}

	public List<ProductBrand> brand() {
		return productBrandDao.brand();
	}

	public List<ProductBrand> select(String name, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		return productBrandDao.select(name, now, pageSize);
	}

	public Integer gettotal(String name, int pageSize) {
		int pageCount = 0;
		int rowCount = productBrandDao.total(name).intValue();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	public ProductBrand select(long code) {
		return productBrandDao.select(code);
	}
}