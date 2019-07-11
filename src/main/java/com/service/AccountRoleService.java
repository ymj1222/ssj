package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AccountRoleDao;
import com.entity.AccountRole;
@Service
public class AccountRoleService {
	@Autowired
AccountRoleDao dao;
	public List<AccountRole> queryAll(int pageNow,int pageSize){
		return dao.select(pageNow, pageSize);
	}
	public void delete(String account,String authCode) {
		dao.delete(account,authCode);
	} 
	public void add(AccountRole accountRole) {
		dao.add(accountRole);
	}
	public List<AccountRole> queryByAccount(String account) {
		return dao.queryByAccount(account);
	}
	public void update(AccountRole accountRole) {
		dao.update(accountRole);
	}
	public Integer gettotal(int pageSize) {
		int pageCount = 0;
		int rowCount = dao.gettotal();
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

}
