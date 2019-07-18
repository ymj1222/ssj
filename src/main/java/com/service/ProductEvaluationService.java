package com.service;

import com.dao.ProductDao;
import com.dao.ProductEvaluationDao;
import com.entity.Product;
import com.entity.ProductEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class ProductEvaluationService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductEvaluationDao productEvaluationDao;
	public void insert(ProductEvaluation ProductEvaluation) {
		Product product = productDao.getProductByCode(ProductEvaluation.getProductCode());
		ProductEvaluation.setProduct(product);
		ProductEvaluation.setProductCode(product.getCode());
		productEvaluationDao.save(ProductEvaluation);
		product.getEvaluation().add(ProductEvaluation);
	}

	public Page<ProductEvaluation> select(Long code, int pageNow, int pageSize) {
		Page<ProductEvaluation> page;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<ProductEvaluation> specification = new Specification<ProductEvaluation>() {
			@Override
			public Predicate toPredicate(Root<ProductEvaluation> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(code !=null && code !=0) {
					Predicate predicate = cb.equal(root.get("productCode"),code);
					query.where(predicate);
					return predicate;
				}
				return null;
			}
		};
		page= productEvaluationDao.findAll(specification,pageable);
		return page;
	}



	
}
