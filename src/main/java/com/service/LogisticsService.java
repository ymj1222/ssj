package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.LogisticsDao;
import com.entity.Logistics;
import com.entity.Pageh;

@Service
public class LogisticsService {
	@Autowired
	private LogisticsDao logisticsDao;

	public void add(Logistics logistics) {
		logisticsDao.save(logistics);
	}

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */
	public void delete(String code) {
		Logistics l=logisticsDao.findByCode(code);
		logisticsDao.deleteById(l.getId());
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public Page<Logistics> select(String name, Pageable pageable){
		Page<Logistics> page = null;
		if(null !=name) {
			page=logisticsDao.findByNameContaining(name, pageable);
		}else{
			page=logisticsDao.findAll(pageable);
		}
		return page;
	}

	/**
	 * �õ�������ҳ��
	 */

	
}
