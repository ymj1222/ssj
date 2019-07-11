package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InformationPushDao;
import com.entity.InformationPush;
import com.util.Pageh;

@Service
public class InformationPushService {
	@Autowired
	private InformationPushDao informationPushDao;

	/**
	 * 添加
	 * 
	 * @param InformationPush
	 */
	public void add(InformationPush InformationPush) {
		informationPushDao.add(InformationPush);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<InformationPush> select(Pageh pageh) {
		List<InformationPush> list = informationPushDao.select(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		Integer pageCount = 0;
		String rowCount1 = informationPushDao.gettotal(pageh);
		Integer rowCount = Integer.parseInt(rowCount1);
		if ((rowCount % pageh.getPageSize()) == 0) {
			pageCount = rowCount / pageh.getPageSize();
		} else {
			pageCount = rowCount / pageh.getPageSize() + 1;
		}
		return pageCount;
	}

}
