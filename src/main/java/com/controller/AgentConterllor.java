package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Terminal;
import com.service.TerminalService;
import com.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Agent;
import com.service.AgentService;

@Controller
public class AgentConterllor {
	@Autowired
	private AgentService agentService;
	@Autowired
	private TerminalService terminalService;
	/**
	 * �ҵ����°��ն˵�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findbind")
	public String findbind(String id, Model model) {
		model.addAttribute("id", id);
		return "forward:/WEB-INF/views/agent/agentbind.jsp";
	}

	/**
	 * �ҵ����ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findagentAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/agent/agentAdd.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findagentList")
	public String findselect() {
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param agent
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agentAdd", method = RequestMethod.POST)
	public String add(Agent agent, String terminalId, Model model, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		agent.setCode(code);
		agent.setCreator(name);
		agent.setUpdator(name);
		agent.setCreateTime(DateUtils.getCurrentDateTime());
		agent.setUpdateTime(DateUtils.getCurrentDateTime());
		agentService.add(agent, terminalId);
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}

	/**
	 * ����idɾ������
	 * 
	 * @param id
	 */
	@RequestMapping("agentDelete")
	public String delete(String id) {
		agentService.delete(id);
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/agentselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Pageh pageh = new Pageh();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		System.out.println(name);
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		pageh.setPageNow(Integer.parseInt(pageNow));
		pageh.setPageSize(Integer.parseInt(pageSize));
		pageh.setObject1(SearchTool.decodeSpecialCharsWhenLikeUseSlash(name));
		org.springframework.data.domain.Page<Agent> page1 = agentService.select(pageh);
		List<Terminal> terminallist = terminalService.queryCode();
		List<Agent> list = page1.getContent();
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
		page.setPageCount(page1.getTotalPages());
		page.setPageNow(Integer.parseInt(pageNow));
		page.setList(list);
		return page;
	}

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/agentUpdate")
	public String update(String id, Map<String, Object> map) {
		Agent agent = agentService.updatequery(id);
		map.put("agent", agent);
		return "forward:/WEB-INF/views/agent/agentupdateSave.jsp";
	}

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param agent
	 * @return
	 */
	@RequestMapping("/agentUpdateSave")
	public String updateSave(Agent agent, HttpServletRequest request) {
		String name = GetNameUtil.getName(request);
		agent.setUpdateTime(DateUtils.getCurrentDateTime());
		agent.setUpdator(name);
		agentService.update(agent);
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}

	/**
	 * ����ն�
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("agentunbind")
	public String unbind(String id) {
		agentService.unbind(id);
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}

	/**
	 * ���°��ն�
	 * 
	 * @param agent
	 * @return
	 */
	@RequestMapping(value = "agentbind", method = RequestMethod.POST)
	public String bind(String id, String terminalid) {
		agentService.bind(id, terminalid);
		return "forward:/WEB-INF/views/agent/agentList.jsp";
	}
}
