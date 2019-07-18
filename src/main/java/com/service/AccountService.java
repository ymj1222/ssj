package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.AccountAuthDao;
import com.dao.AccountDao;
import com.dao.AuthDao;
import com.entity.Account;
import com.entity.AccountAuth;
import com.entity.Auth;

@Service
public class AccountService {
	
	@Autowired
     AccountDao dao;
	@Autowired
	AuthDao ad;
	@Autowired
	AccountAuthDao aad;

	public Page<Account> queryAll(String name, Pageable pageable){
		Page<Account> page = null;
		if(null !=name) {
			 page=dao.findByNameContaining(name, pageable);
		}else{
			page=dao.findAll(pageable);
		}
		return page;
	}
	public void delete(String state,String code) {
		dao.delete(state,code);
	} 
	public void add(Account account) {
		List<Auth> list=ad.findAll();
		for (int i = 0; i < list.size(); i++) {	
			AccountAuth aa=new AccountAuth();
			aa.setAccount(account.getAccount());
			aa.setAuthCode(list.get(i).getCode());
			aad.save(aa);
		}
		dao.save(account);
	}
	public Account login(String account,String password) {
		return dao.findByAccountAndPassword(account, password);
	}
	public Account valid(String account, String password) throws Exception {
		return dao.findByAccountAndPasswordAndState(account,password,"0");
	}
	public Account queryByAccount(String account) {
		return dao.findByAccount(account);
	}
	public void update(Account account) {
		dao.update(account.getPassword(),account.getAccount());
	}
public Account daoGou(String code) {
	return dao.findByCode(code);
}
}
