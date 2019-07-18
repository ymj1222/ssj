package com.service;

import java.util.List;

import com.dao.AgentDao;
import com.dao.TerminalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDao;
import com.entity.Agent;
import com.entity.Terminal;
import com.util.Pageh;

@Service
public class AgentService {

@Autowired
	private TerminalDao terminalDao;
	@Autowired
	private ProductDao productDao;

	@Autowired
	private AgentDao agentDa;

	public void add(Agent agent, String terminalId) {
	Terminal terminal= 	terminalDao.getById(Integer.valueOf(terminalId));
	agent.setTerminalCode(terminal.getCode());
	agent.setTerminal(terminal);
		agentDa.save(agent);

	}

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(String id) {
		Integer code = new Integer(id);
		productDao.down(code);
		Agent agent = agentDa.getById(code);
		agentDa.delete(agent);
	}

	/**
	 * ��ģ�������ķ�ҳ
	 * 
	 * @return
	 */
	public Page<Agent> select(Pageh pageh) {
		if(pageh.getObject1()!=null){
			Pageable pageable =  PageRequest.of(pageh.getPageNow()-1, pageh.getPageSize());
			Page<Agent> page=agentDa.getByNameContainingOrderByIdDesc(pageh.getObject1(),pageable);
			return page;
		}else {
			Pageable pageable =  PageRequest.of(pageh.getPageNow(), pageh.getPageSize(),new Sort(Sort.Direction.DESC,"id"));
			Page<Agent> page=agentDa.findAll(pageable);
			return page;
		}
	}



	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Agent updatequery(String id) {
		Integer code = new Integer(id);
		Agent agent = agentDa.getById(code);
		return agent;
	}

	/**
	 * �޸�����
	 * 
	 * @param
	 * @param
	 */
	public void update(Agent agent) {
		agentDa.save(agent);
	}

	/**
	 * ������ն�
	 */
	public void unbind(String id) {
	Agent agent=agentDa.getById(Integer.parseInt(id));
	if(agent!=null){
		agent.setTerminal(null);
		agent.setTerminalCode(null);
		agentDa.save(agent);
	}
	}

	/**
	 * ���°��ն�
	 * 
	 * @param
	 */
	public void bind(String id, String terminalId) {
		if (id != null && !id.equals("") && terminalId != null && !terminalId.equals("")) {
		Agent agent=agentDa.getById(Integer.valueOf(id));
		Terminal terminal=	terminalDao.getById(Integer.valueOf(terminalId));
			if(agent!=null && terminal!=null){
				agent.setTerminal(terminal);
				agent.setTerminalCode(terminal.getCode());
				agentDa.save(agent);
			}
		} else {
			return;
		}
	}

	/**
	 * �õ�����������Ϣ
	 * 
	 * @return
	 */
	public List<Agent> queryagentCode() {
		return agentDa.findAll();
	}
}
