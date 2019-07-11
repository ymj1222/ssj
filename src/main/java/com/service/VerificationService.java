package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VerificationDao;
import com.entity.Verification;

@Service
public class VerificationService {
	@Autowired
	private VerificationDao userDao;

	public Verification valid(String account, String password) throws Exception {
		return userDao.queryByCode(account,password);		
	}
}