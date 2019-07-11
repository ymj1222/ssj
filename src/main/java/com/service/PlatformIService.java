package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PlatformIDao;
import com.entity.PlatformI;
@Service
public class PlatformIService {
	
	@Autowired
     PlatformIDao dao;
	
	public PlatformI query() {
		return dao.query();
	}
	public void update(PlatformI platformI) {
		dao.update(platformI);
	}

}
