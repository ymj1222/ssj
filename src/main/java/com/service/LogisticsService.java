package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LogisticsDao;
import com.entity.Logistics;
import com.entity.Pageh;

@Service
public class LogisticsService {
	@Autowired
	private LogisticsDao logisticsDao;

	public void add(Logistics logistics) {
		logisticsDao.add(logistics);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String code) {
		logisticsDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Logistics> select(Pageh ph) {
		return logisticsDao.select(ph);
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh ph) {
		int pageCount = 0;
		int rowCount = logisticsDao.gettotal(ph);
		if ((rowCount % ph.getPageSize()) == 0) {
			pageCount = rowCount / ph.getPageSize();
		} else {
			pageCount = rowCount / ph.getPageSize() + 1;
		}
		return pageCount;
	}

	
}
