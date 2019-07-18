package com.service;

import java.util.List;

import com.dao.TerminalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdvertisementDao;
import com.entity.Advertisement;
import com.util.Pageh;

@Service
public class AdvertisementService {
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private TerminalDao terminal;
	public void add(Advertisement advertisement) {
		advertisementDao.add(advertisement);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String code) {
		advertisementDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Advertisement> select(Pageh pageh) {
		List<Advertisement> list = advertisementDao.getPersons(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		int rowCount = advertisementDao.gettotal(pageh);
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
	public Advertisement updatequery(String code) {
		Advertisement te = advertisementDao.upquery(code);
		return te;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Advertisement advertisement) {
		advertisementDao.update(advertisement);
	}
	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Advertisement getquery(String photo) {
		Advertisement te = advertisementDao.getquery(photo);
		return te;
	}
	
	
	public List<Advertisement> queryl() {
		List<Advertisement> list = advertisementDao.getList();
		return list;
	}
	
}
