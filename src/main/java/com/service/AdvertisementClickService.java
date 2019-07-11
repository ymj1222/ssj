package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdvertisementClickDao;
import com.entity.AdvertisementClick;
import com.util.Pageh;

@Service
public class AdvertisementClickService {
	@Autowired
	private AdvertisementClickDao advertisementClickDao;

	public void add(AdvertisementClick advertisementClick) {
		advertisementClickDao.add(advertisementClick);
	}


	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<AdvertisementClick> select(Pageh pageh) {
		List<AdvertisementClick> list = advertisementClickDao.getPersons(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		int rowCount = advertisementClickDao.gettotal(pageh);
		if ((rowCount % pageh.getPageSize()) == 0) {
			pageCount = rowCount / pageh.getPageSize();
		} else {
			pageCount = rowCount / pageh.getPageSize() + 1;
		}
		return pageCount;
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public AdvertisementClick updatequery(String code) {
		AdvertisementClick ter = advertisementClickDao.upquery(code);
		return ter;
	}
	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(AdvertisementClick advertisementClick) {
		advertisementClickDao.update(advertisementClick);
	}
	
}
