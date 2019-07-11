package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AccountAuthDao;
import com.entity.AccountAuth;
@Service
public class AccountAuthService {
	@Autowired
AccountAuthDao dao;
	public void delete(String account,String authCode) {
		dao.delete(account,authCode);
	} 
	public void add(AccountAuth auth) {
		dao.add(auth);
	}
	public List<AccountAuth> queryByAccount(String account) {
		return dao.queryByAccount(account);
	}

}
