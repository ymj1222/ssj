package com.service;

import com.dao.ShoppingGuideDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.ShoppingGuide;
import com.util.Pageh;

@Service
public class ShoppingGuideService {
	@Autowired
	private ShoppingGuideDao shopp;

	public void add(ShoppingGuide shoppingGuide) {
		shopp.save(shoppingGuide);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */

	public void delete(String id) {
		ShoppingGuide shoppingGuide =shopp.getById(Integer.valueOf(id));
		shopp.delete(shoppingGuide);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public Page<ShoppingGuide> select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			Page<ShoppingGuide>	page=shopp.getByNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<ShoppingGuide> page=shopp.findAll(pageable);
			return page;
		}
	}


	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public ShoppingGuide updatequery(String id) {
		ShoppingGuide shoppingGuide = shopp.getById(Integer.valueOf(id));
		return shoppingGuide;
	}

	/**
	 * 修改数据
	 *
	 */
	public void update(ShoppingGuide shoppingGuide) {
		shopp.save(shoppingGuide);
	}

	/**
	 * 当客服被抓去服务的时候，将在线状态改成忙线
	 */
	public void updatetypebusy(String id) {
		ShoppingGuide shoppingGuide=shopp.getById(Integer.parseInt(id));
		shoppingGuide.setType(1003);
		shopp.save(shoppingGuide);
	}

	/**
	 * 当客服服务完成之后，或者上线的时候将状态改为在线
	 */
	public void updatetypeon(String id) {
		ShoppingGuide shoppingGuide=shopp.getById(Integer.parseInt(id));
		shoppingGuide.setType(1001);
		shopp.save(shoppingGuide);
	}

	/**
	 * 客服下线，将状态改为下线
	 */
	public void updatetypeoff(String id) {
		ShoppingGuide shoppingGuide=shopp.getById(Integer.parseInt(id));
		shoppingGuide.setType(1002);
		shopp.save(shoppingGuide);
	}

	/**
	 * 上线
	 * @param code
	 */
	public void getidupdate(String code) {
		if(code!=null && !code.equals("")) {
			ShoppingGuide shoppingGuide=shopp.getByCode(code);
			if(shoppingGuide!=null){
				shoppingGuide.setType(1001);
				shopp.save(shoppingGuide);
			}
		}
	}
	
	/**
	 * 下线
	 * @param code
	 */
	public void getidupdatedown(String code) {
		if(code!=null && !code.equals("")) {
			ShoppingGuide shoppingGuide=shopp.getByCode(code);
			if(shoppingGuide!=null){
				shoppingGuide.setType(1002);
				shopp.save(shoppingGuide);
			}
		}
	}
}
