package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
			RoleAuth ra=new RoleAuth();
			ra.setAuthCode(str[i]);
			ra.setRoleCode(auth.getRoleCode());
			dao.save(ra);
		}
	}
	public List<RoleAuth> queryByRoleCode(String roleCode) {
		return dao.findByRoleCode(roleCode);
	}
	public void update(RoleAuth roleAuth) {
		dao.update(roleAuth.getAuthCode(),roleAuth.getRoleCode());
	}
	public Page<RoleAuth> queryAll(int pageNow, int pageSize) {
		Pageable pageable= PageRequest.of(pageNow,pageSize);
		return dao.findAll(pageable);
	}
public void delete(String roleCode,String authCode) {
		RoleAuth r=dao.findByRoleCodeAndAuthCode(roleCode,authCode);
	dao.deleteById(r.getId());
}
}
