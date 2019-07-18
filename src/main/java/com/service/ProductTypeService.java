package com.service;

import com.dao.ProductTypeDao;
import com.entity.ProductType;
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
public class ProductTypeService {
	@Autowired
	private ProductTypeDao productTypeDao1;
	@Transactional
	public void insert(ProductType productType) {
		productTypeDao1.save(productType);
	}

	@Transactional
	public void delete(long code) {
		productTypeDao1.updateproduct(code);
		productTypeDao1.deleteByCode(code);
	}

	public List<ProductType> selectoption() {
		return productTypeDao1.findAll();
	}

	public Page<ProductType> select(String name, int pageNow, int pageSize) {
		Page<ProductType> page;
		Sort sort = new Sort(Sort.Direction.DESC, "code");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
			Specification<ProductType> specification = new Specification<ProductType>() {
				@Override
				public Predicate toPredicate(Root<ProductType> root,
											 CriteriaQuery<?> query, CriteriaBuilder cb) {
					if(name !=null && name !="") {

						Predicate predicate = cb.like(root.get("name").as(String.class),"%"+name+"%");
						return predicate;

					}
					return null;
				}
			};
			page= productTypeDao1.findAll(specification,pageable);
		return page;
	}

	public ProductType select(long code) {
		return productTypeDao1.getByCode(code);
	}
}
