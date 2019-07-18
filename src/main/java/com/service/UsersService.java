package com.service;

import java.util.List;

import com.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.UsersDao;
import com.entity.Pageh;
import com.entity.Users;
@Service
public class UsersService {
	@Autowired
UsersDao dao;
	public Page<Users> queryAll(String name, Pageable pageable){
		Page<Users> page = null;
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
	public void add(Users user) {
		dao.save(user);
	}
	public Users queryByCode(String code) {
		return dao.findByCode(code);
	}
	public String queryByAccount(String account) {
		return dao.findByAccount(account).getCode();
	}
	public void update(Users user) {
		dao.update(user.getName(),user.getPhone(),user.getWechat(),user.getSex(),user.getCity(),user.getAge(),user.getLevelMark(),user.getGoldCoin(),user.getIntegral(),user.getCode());
	}

}
