package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.OptionLogDao;
import com.entity.OptionLog;
import com.util.DateUtils;
@Service
public class OptionLogService {
	@Autowired
	OptionLogDao dao;
	public Page<OptionLog> queryAll(int pageNow, int pageSize){
		Pageable pageable= PageRequest.of(pageNow,pageSize, Sort.by(Sort.Direction.DESC, "id"));
		return dao.findAll(pageable);
	}
	public void add(int type,HttpServletRequest request) {
		HttpSession hs = request.getSession();
    	OptionLog o=new OptionLog();
    	o.setOperator(hs.getAttribute("account").toString());
    	o.setOperateTime(DateUtils.getCurrentDateTime());
    	o.setOperateType(type);
		dao.save(o);
	}

}
