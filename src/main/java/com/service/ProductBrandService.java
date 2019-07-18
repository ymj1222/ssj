package com.service;

import com.dao.ProductBrandDao;
import com.entity.ProductBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ProductBrandService {
	@Autowired
	private ProductBrandDao productBrandDao1;
	@Transactional
	public void insert(ProductBrand productBrand) {
		productBrandDao1.save(productBrand);
	}

	@Transactional
	public void delete(long code) {
		productBrandDao1.updateproduct(code);
		productBrandDao1.deleteByCode(code);
	}

	public List<ProductBrand> brand() {
		return productBrandDao1.findAll();
	}

	public Page<ProductBrand> select(String name, int pageNow, int pageSize) {
		Page<ProductBrand> page;
		Sort sort = new Sort(Sort.Direction.DESC, "code");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<ProductBrand> specification = new Specification<ProductBrand>() {
			@Override
			public Predicate toPredicate(Root<ProductBrand> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(name !=null && name !="") {

					Predicate predicate = cb.like(root.get("name").as(String.class),"%"+name+"%");
					return predicate;

				}
				return null;
			}
		};
		page= productBrandDao1.findAll(specification,pageable);
		return page;
	}

	public ProductBrand select(long code) {
		return productBrandDao1.getProductBrandByCode(code);
	}
}