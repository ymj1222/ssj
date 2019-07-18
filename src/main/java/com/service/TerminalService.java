package com.service;

import java.util.List;

import com.dao.TerminalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Terminal;
import com.util.Pageh;

@Service
public class TerminalService {
	@Autowired
	private TerminalDao terminalD;

	public void add(Terminal ter) {
		terminalD.save(ter);
	}

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */
	public void delete(String id) {
		terminalD.deleteById(Integer.valueOf(id));
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public Page<Terminal> select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			Page<Terminal> page=terminalD.getByNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<Terminal> page=terminalD.findAll(pageable);
			return page;
		}
	}

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Terminal updatequery(String id) {
		Integer code = new Integer(id);
		Terminal ter = terminalD.getById(code);
		return ter;
	}

	/**
	 * �޸�����
	 * 
	 * @param
	 * @param meetroom
	 */
	public void update(Terminal meetroom) {
		terminalD.save(meetroom);
	}

	/**
	 * �õ������ն˵�code
	 * 
	 * @return
	 */
	public List<Terminal> queryCode() {
		List<Terminal> list = terminalD.findAll();
		return list;
	}
}
