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
		return dao.findAll().get(0);
	}
	public void update(PlatformI p) {
		if(null!=p.getLogo()&&null==p.getQr()) {
			dao.update(p.getLogo(),p.getCompanyWebsite(),p.getTel(),p.getCode());
		}if(null!=p.getQr()&&null==p.getLogo()){
			dao.update1(p.getQr(),p.getCompanyWebsite(),p.getTel(),p.getCode());
		}else if(null!=p.getLogo()&&null!=p.getQr()){
			dao.update2(p.getLogo(),p.getCompanyWebsite(),p.getTel(),p.getQr(),p.getCode());
		}
	}

}
