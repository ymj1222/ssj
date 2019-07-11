package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.dao.ProductEvaluationDao;
import com.entity.Product;
import com.entity.ProductEvaluation;

@Service
public class ProductEvaluationService {
	@Autowired
	private ProductEvaluationDao productEvaluationDao;
	@Autowired
	private ProductDao productDao;

	public void insert(ProductEvaluation ProductEvaluation) {
		Product product = productDao.updatequery(ProductEvaluation.getProductCode());
		ProductEvaluation.setProduct(product);
		productEvaluationDao.insert(ProductEvaluation);
		product.getEvaluation().add(ProductEvaluation);
	}

	public List<ProductEvaluation> select(Long code, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		return productEvaluationDao.select(code, now, pageSize);
	}

	public Integer gettotal(Long code, int pageSize) {
		int pageCount = 0;
		int rowCount = productEvaluationDao.page(code).intValue();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	
}
