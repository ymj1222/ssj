package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleAuthDao;
import com.entity.RoleAuth;
@Service
public class RoleAuthService {
	@Autowired
RoleAuthDao dao;
	public void add(RoleAuth auth) {
		String str[]=auth.getAuthCode().split(",");
		for (int i = 0; i < str.length; i++) {
			dao.add(auth.getRoleCode(),str[i]);
		}
	}
	public List<RoleAuth> queryByRoleCode(String roleCode) {
		return dao.queryByRoleCode(roleCode);
	}
	public void update(RoleAuth roleAuth) {
		dao.update(roleAuth);
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
	public List<RoleAuth> queryAll(int pageNow,int pageSize) {
		return dao.select(pageNow, pageSize);
	}
public void delete(String roleCode,String authCode) {
	dao.delete(roleCode, authCode);
}
}
