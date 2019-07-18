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
		AccountAuth a=dao.findByAccountAndAuthCode(account,authCode);
		dao.deleteById(a.getId());
	} 
	public void add(AccountAuth auth) {
		dao.save(auth);
	}
	public List<AccountAuth> queryByAccount(String account) {
		return dao.findByAccount(account);
	}

}
