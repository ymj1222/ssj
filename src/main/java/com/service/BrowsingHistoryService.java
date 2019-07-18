package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.BrowsingHistoryDao;
import com.entity.BrowsingHistory;
@Service
public class BrowsingHistoryService {
	@Autowired
	BrowsingHistoryDao dao;
	
	public Page<BrowsingHistory> queryAll(int pageNow, int pageSize, String code){
		Pageable pageable= PageRequest.of(pageNow,pageSize, Sort.by(Sort.Direction.DESC, "id"));
		return dao.findByUsersCode(code,pageable);
	}
	public void delete(String code) {
		dao.deleteByCode(code);
	} 
	public void add(BrowsingHistory auth) {
		dao.save(auth);
	}


}
