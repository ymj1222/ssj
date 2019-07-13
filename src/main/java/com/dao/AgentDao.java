package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Agent;
import com.entity.Terminal;
import com.util.DateUtils;
import com.util.Pageh;

@Repository
public class AgentDao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * ���
	 * 
	 * @param
	 */
	@Transactional
	public void add(Agent agent, String terminalId) {
		if (!terminalId.equals("") && terminalId != null) {
			Terminal ter = entityManager.find(Terminal.class, new Integer(terminalId));
			agent.setTerminalCode(ter.getCode());
			agent.setTerminal(ter);
		}
		entityManager.persist(agent);
	};

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */

	public void delete(int id) {
		Agent agent = entityManager.find(Agent.class, id);
		entityManager.remove(agent);
	};

	/**
	 * ��ģ�������ķ�ҳ
	 * 
	 * @param pageh
	 * @return
	 */
	public List<Agent> select(Pageh pageh) {
		String jpql = "select t from Agent t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like '%'||?1||'%'";
		}
		jpql += " order by id desc";
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		return query.getResultList();
	};

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	@Transactional
	public String gettotal(Pageh pageh) {
		String jpql = "select count(t) from Agent t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like '%'||?1||'%'";
		}
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		Long count = (Long) query.getResultList().get(0);
		return count.toString();
	};

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Agent updatequery(int id) {
		Agent agent = entityManager.find(Agent.class, id);
		return agent;
	};

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(Agent agent) {
		String jpql = "update zj_agent a set a.name = ?,a.phone = ?,addr =?,updateTime=?,updator=? where a.id = ?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, agent.getName());
		query.setParameter(2, agent.getPhone());
		query.setParameter(3, agent.getAddr());
		query.setParameter(4, agent.getUpdateTime());
		query.setParameter(5, agent.getUpdator());
		query.setParameter(6, agent.getId());
		query.executeUpdate();
	};

	/**
	 * ����ն˰�
	 * 
	 * @param id
	 */
	@Transactional
	public void unbind(int id) {
		String jpql = "update zj_agent a set a.terminal_id = null,a.terminalCode = null where a.id = ?";
		Query query = entityManager.createNativeQuery(jpql);
		query.setParameter(1, id);
		query.executeUpdate();
	};

	/**
	 * ���°��ն�
	 * 
	 * @param terminalCode
	 */
	@Transactional
	public void bind(int id, int terminalId) {
		Agent agent = entityManager.find(Agent.class, id);
		Terminal ter = entityManager.find(Terminal.class, new Integer(terminalId));
		agent.setTerminal(ter);
		agent.setTerminalCode(ter.getCode());
		agent.setUpdateTime(DateUtils.getCurrentDateTime());
	};

	/**
	 * �õ�����������Ϣ
	 * 
	 * @return
	 */
	public List<Agent> queryagentCode() {
		String sql = "from Agent";
		Query query = entityManager.createQuery(sql);
		List<Agent> list = query.getResultList();
		return list;
	}

	public Agent find(Integer id) {
		return entityManager.find(Agent.class, id);
	}
}
