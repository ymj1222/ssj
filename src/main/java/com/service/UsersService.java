package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UsersDao;
import com.entity.Pageh;
import com.entity.Users;
@Service
public class UsersService {
	@Autowired
UsersDao dao;
	public List<Users> queryAll(Pageh ph){
		return dao.select(ph);
	}
	public void delete(String state,String code) {
		dao.delete(state,code);
	} 
	public void add(Users user) {
		dao.add(user);
	}
	public Users queryByCode(String code) {
		return dao.queryByCode(code);
	}
	public String queryByAccount(String account) {
		return dao.queryByAccount(account);
	}
	public void update(Users user) {
		dao.update(user);
	}
	public Integer gettotal(Pageh pg) {
		int pageCount = 0;
		int rowCount = dao.gettotal(pg);
		if ((rowCount % pg.getPageSize()) == 0) {
			pageCount = rowCount / pg.getPageSize();
		} else {
			pageCount = rowCount / pg.getPageSize() + 1;
		}
		return pageCount;
	}

}
