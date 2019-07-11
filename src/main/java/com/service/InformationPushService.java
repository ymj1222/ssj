package com.service;

import java.util.ArrayList;
import java.util.List;

import com.entity.Pageh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InformationPushDao;
import com.entity.InformationPush;

@Service
public class InformationPushService {
	@Autowired
	private InformationPushDao informationPushDao;

//	/**
//	 * 添加
//	 *
//	 * @param InformationPush
//	 */
//	public void add(InformationPush InformationPush) {
//		informationPushDao.add(InformationPush);
//	}

	/**
	 * 添加
	 * @param InformationPush
	 */
	public void add(InformationPush InformationPush){
		informationPushDao.save(InformationPush);
	}
	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<InformationPush> select(Pageh pageh) {
		List<InformationPush> list = new ArrayList<>();
		if(pageh.getPhone()!=null && pageh.getPhone()!=""){
			list= informationPushDao.selecttwo(pageh.getPhone(),pageh.getPageNow(),pageh.getPageSize());
		}else{
			list= informationPushDao.select(pageh.getPageNow(),pageh.getPageSize());
		}
		return list;
	}

	/**
	 * 得到数据总页数
	 */
//	public Integer gettotal(Pageh pageh) {
//		Integer pageCount = 0;
//		String rowCount1 = informationPushDao.gettotal(pageh);
//		Integer rowCount = Integer.parseInt(rowCount1);
//		if ((rowCount % pageh.getPageSize()) == 0) {
//			pageCount = rowCount / pageh.getPageSize();
//		} else {
//			pageCount = rowCount / pageh.getPageSize() + 1;
//		}
//		return pageCount;
//	}

	/**
	 * 得到数据条数
	 * @param pageh
	 * @return
	 */
	public Integer gettotal(Pageh pageh){
		Integer pageCount = 0;
		Integer rowCount = 0;
		if(pageh.getPhone()!=null && pageh.getPhone()!=""){
			rowCount= informationPushDao.gettotaltwo(pageh.getPhone());
		}else{
			rowCount= informationPushDao.gettotal();
		}
		if ((rowCount % pageh.getPageSize()) == 0) {
			pageCount = rowCount / pageh.getPageSize();
		} else {
			pageCount = rowCount / pageh.getPageSize() + 1;
		}
		return pageCount;
	}
}
