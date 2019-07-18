package com.service;

import com.dao.ServiceReCordDao;
import com.dao.ShoppingGuideDao;
import com.entity.ShoppingGuide;
import org.springframework.beans.factory.annotation.Autowired;


import com.entity.ServiceRecord;
import com.util.Pageh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ServiceRecordService {

	@Autowired
	private ServiceReCordDao ervice;

	@Autowired
	private ShoppingGuideDao shopp;
	/**
	 * 添加
	 * 
	 * @param
	 */

	public void add(ServiceRecord serviceRecord, String ShoppingGuideId){
		ShoppingGuide shoppingGuide =shopp.getById(Integer.parseInt(ShoppingGuideId));
		serviceRecord.setShoppingGuideCode(shoppingGuide.getCode());
		serviceRecord.setShoppingGuideName(shoppingGuide.getName());
		serviceRecord.setShoppingGuide(shoppingGuide);
		ervice.save(serviceRecord);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public Page select(Pageh pageh) {
//		Page<ServiceRecord> Page =null;

		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			Page<ServiceRecord>	page=ervice.getByShoppingGuideNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<ServiceRecord> page=ervice.findAll(pageable);
			return page;
		}
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getStaisfaction().equals("3001")) {
//				list.get(i).setStaisfaction("极差");
//			} else if (list.get(i).getStaisfaction().equals("3002")) {
//				list.get(i).setStaisfaction("差");
//			} else if (list.get(i).getStaisfaction().equals("3003")) {
//				list.get(i).setStaisfaction("一般");
//			} else if (list.get(i).getStaisfaction().equals("3004")) {
//				list.get(i).setStaisfaction("满意");
//			} else if (list.get(i).getStaisfaction().equals("3005")) {
//				list.get(i).setStaisfaction("非常满意");
//			}
//		}
//		return list;
	}

}
