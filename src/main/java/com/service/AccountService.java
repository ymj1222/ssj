package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AccountAuthDao;
import com.dao.AccountDao;
import com.dao.AuthDao;
import com.entity.Account;
import com.entity.AccountAuth;
import com.entity.Auth;
import com.entity.Pageh;
import com.entity.Verification;
@Service
public class AccountService {
	
	@Autowired
     AccountDao dao;
	@Autowired
	AuthDao ad;
	@Autowired
	AccountAuthDao aad;
	
	public List<Account> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public void delete(String state,String code) {
		dao.delete(state,code);
	} 
	public void add(Account account) {
		List<Auth> list=ad.query();
		for (int i = 0; i < list.size(); i++) {	
			AccountAuth aa=new AccountAuth();
			aa.setAccount(account.getAccount());
			aa.setAuthCode(list.get(i).getCode());
			aad.add(aa);
		}
		dao.add(account);
	}
	public Verification login(String account,String password) {
		return dao.login(account, password);
	}
	public Account queryByAccount(String account) {
		return dao.queryByAccount(account);
	}
	public void update(Account account) {
		dao.update(account);
	}
	public Integer gettotal(Pageh ph) {
		int pageCount = 0;
		int rowCount = dao.gettotal(ph);
		if ((rowCount % ph.getPageSize()) == 0) {
			pageCount = rowCount / ph.getPageSize();
		} else {
			pageCount = rowCount / ph.getPageSize() + 1;
		}
		return pageCount;
	}
public Account daoGou(String code) {
	return dao.daoGou(code);
}
}
