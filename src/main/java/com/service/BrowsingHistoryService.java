package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BrowsingHistoryDao;
import com.entity.BrowsingHistory;
@Service
public class BrowsingHistoryService {
	@Autowired
	BrowsingHistoryDao dao;
	
	public List<BrowsingHistory> queryAll(int pageNow,int pageSize,String code){
		return dao.select(pageNow, pageSize,code);
	}
	public void delete(String code) {
		dao.delete(code);
	} 
	public void add(BrowsingHistory auth) {
		dao.add(auth);
	}
	public Integer gettotal(int pageSize,String userCode) {
		int pageCount = 0;
		int rowCount = dao.gettotal(userCode);
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

}
