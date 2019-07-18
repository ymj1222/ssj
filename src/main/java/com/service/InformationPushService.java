package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.InformationPushDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.entity.InformationPush;
import com.util.Pageh;

import javax.persistence.criteria.*;

@Service
public class InformationPushService {

	@Autowired
	private InformationPushDao inforDao;
	public void add(InformationPush InformationPush){
		inforDao.save(InformationPush);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public Page select(Pageh pageh) {
		List<InformationPush> list = new ArrayList<>();
		Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
		Sort sort = new Sort(order1);
		PageRequest pageable = new PageRequest(pageh.getPageNow()-1, pageh.getPageSize(),sort);
		Specification<InformationPush> specification = new Specification<InformationPush>() {
			@Override
			public Predicate toPredicate(Root<InformationPush> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate =null;
				if(pageh.getPhone()!=null){
					predicate=cb.like(root.get("phone"),"%"+pageh.getPhone()+"%");
				}else {
					predicate=cb.like(root.get("phone"),"%%");
				}
				return predicate;
			}
		};
		Page<InformationPush> page = inforDao.findAll(specification,pageable);
		return page;
	}
}
