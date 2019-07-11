package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AgentDao;
import com.dao.ProductDao;
import com.entity.Agent;
import com.entity.Terminal;
import com.util.Pageh;

@Service
public class AgentService {
	@Autowired
	private AgentDao agentDao;
	@Autowired
	private TerminalService terminalService;
	@Autowired
	private ProductDao productDao;

	public void add(Agent agent, String terminalId) {
		agentDao.add(agent, terminalId);
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
		agentDao.delete(code);
	}

	/**
	 * 带模糊搜索的分页
	 * 
	 * @return
	 */
	public List<Agent> select(Pageh pageh) {
		List<Terminal> terminallist = terminalService.queryCode();
		List<Agent> list = agentDao.select(pageh);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < terminallist.size(); j++) {
				if (list.get(i).getTerminalCode() != null) {
					if (terminallist.get(j).getCode() != null && !terminallist.get(j).getCode().equals("")) {
						if (list.get(i).getTerminalCode().equals(terminallist.get(j).getCode())) {
							list.get(i).setTerminalCode(terminallist.get(j).getName());
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		Integer pageCount = 0;
		String rowCounts = agentDao.gettotal(pageh);
		Integer rowCount = Integer.valueOf(rowCounts);
		if ((rowCount % pageh.getPageSize()) == 0) {
			pageCount = rowCount / pageh.getPageSize();
		} else {
			pageCount = rowCount / pageh.getPageSize() + 1;
		}
		return pageCount;
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Agent updatequery(String id) {
		Integer code = new Integer(id);
		Agent agent = agentDao.updatequery(code);
		return agent;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Agent agent) {
		agentDao.updateSave(agent);
	}

	/**
	 * 解除绑定终端
	 */
	public void unbind(String id) {
		agentDao.unbind(Integer.parseInt(id));
	}

	/**
	 * 重新绑定终端
	 * 
	 * @param agent
	 */
	public void bind(String id, String terminalId) {
		if (id != null && !id.equals("") && terminalId != null && !terminalId.equals("")) {
			agentDao.bind(Integer.parseInt(id), Integer.parseInt(terminalId));
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
		return agentDao.queryagentCode();
	}
}
