package com.service;

import com.dao.SpecialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Special;
import com.util.Pageh;

@Service
public class SpecialService {
	@Autowired
	private SpecialDao specialDa;



	
	
	public void add(Special special) {
		specialDa.save(special);
	}

	/**
	 * 根据code删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
		specialDa.delete(specialDa.getByCode(id));
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public Page<Special> select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			org.springframework.data.domain.Page<Special> page=specialDa.getByNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<Special> page=specialDa.findAll(pageable);
			return page;
		}
	}



	/**
	 * 得到要修改的数据
	 * 
	 * @param
	 * @return
	 */
	public Special updatequery(String code) {
		Special special = specialDa.getByCode(code);
		return special;
	}

	/**
	 * 修改数据
	 * 
	 * @param
	 * @param
	 */
	public void update(Special special) {
		specialDa.save(special);
	}
}
