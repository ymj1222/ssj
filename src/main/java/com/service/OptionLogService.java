package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OptionLogDao;
import com.entity.OptionLog;
import com.util.DateUtils;
@Service
public class OptionLogService {
	@Autowired
	OptionLogDao dao;
	public List<OptionLog> queryAll(int pageNow,int pageSize){
		return dao.select(pageNow, pageSize);
	}
	public void add(int type,HttpServletRequest request) {
		HttpSession hs = request.getSession();
    	OptionLog o=new OptionLog();
    	o.setOperator(hs.getAttribute("account").toString());
    	o.setOperateTime(DateUtils.getCurrentDateTime());
    	o.setOperateType(type);
		dao.add(o);
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
