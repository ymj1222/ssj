package com.service;

import com.dao.ProductDao;
import com.dao.WarehouseDao;
import com.entity.Product;
import com.entity.Warehouse;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
	@Autowired
	private WarehouseDao warehouseDao1;
    @Autowired
	private ProductDao product;
	public Page<Warehouse> selectwarehouse(String name, int pageNow, int pageSize) {
		Page<Warehouse> page;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<Warehouse> specification = new Specification<Warehouse>() {
			@Override
			public Predicate toPredicate(Root<Warehouse> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> lists = new ArrayList<Predicate>();
				List<Product> list =product.getCodeByAuditStatusAndIsEffective(3,1);
				Long[]codes= new Long[list.size()];
				for (int i = 0; i <list.size() ; i++) {
					codes[i]=list.get(i).getCode();
				}
			CriteriaBuilder.In<Long> in=cb.in(root.get("productCode").as(Long.class));
				for (Long productCode : codes) {
					in.value(productCode);
				}

				if (name != null && name !="") {
					Predicate predicate = cb.like(root.get("productName").as(String.class),"%"+name+"%");
					query.where(predicate,in);
					return null;
				}
				query.where(in);
				return null;
			}
		};
		page= warehouseDao1.findAll(specification,pageable);
		List<Warehouse> list = page.getContent();
		for (int i = 0; i <list.size(); i++) {
			list.get(i).setProductName(list.get(i).getProduct().getName());
		}
		return page;
	}


}
