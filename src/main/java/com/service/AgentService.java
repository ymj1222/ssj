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
	 * 根据id删除
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
	 * 带模糊搜索的分页
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
	 * 得到要修改的数据
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
	 * 修改数据
	 * 
	 * @param
	 * @param
	 */
	public void update(Agent agent) {
		agentDa.save(agent);
	}

	/**
	 * 解除绑定终端
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
	 * 重新绑定终端
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
	 * 得到代理所有信息
	 * 
	 * @return
	 */
	public List<Agent> queryagentCode() {
		return agentDa.findAll();
	}
}
