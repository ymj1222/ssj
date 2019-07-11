package com.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Terminal;
import com.util.Pageh;

@Repository
public class TerminalDao {
	@PersistenceContext
	private EntityManager entityManager;

	public String findadd() {
		return "forward:/WEB-INF/views/terminalAdd.jsp";
	}

	/**
	 * 添加
	 * 
	 * @param terminal
	 */
	@Transactional
	public void add(Terminal ter) {
		entityManager.persist(ter);
	};

	/**
	 * 根据id删除 先将跟这个终端绑定的代理解除绑定，然后再删除这个终端
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		Terminal ter = entityManager.find(Terminal.class, id);
		String jpql1 = "update zj_agent a set a.terminal_id = null,a.terminalCode = null where a.terminal_id = ?";
		Query query1 = entityManager.createNativeQuery(jpql1);
		query1.setParameter(1, ter.getId());
		query1.executeUpdate();
		entityManager.remove(ter);
	};

	@SuppressWarnings("unchecked")
	public List<Terminal> select(Pageh pageh) {
		String jpql = "select t from Terminal t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like '%'||?||'%/' ";
		}
		jpql+=" order by t.id desc";
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		query.setFirstResult(pageh.getPageNow());
		query.setMaxResults(pageh.getPageSize());
		List<Terminal> list = query.getResultList();
		return list;
	};

	/**
	 * 得到数据条数
	 * 
	 * @return
	 */
	public String gettotal(Pageh pageh) {
		String jpql = "select count(t) from Terminal t";
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			jpql += " where t.name like '%'||?||'%/'";
		} 
		Query query = entityManager.createQuery(jpql);
		if (pageh.getObject1() != null && !pageh.getObject1().equals("")) {
			query.setParameter(1, pageh.getObject1());
		}
		Long count = (Long) query.getResultList().get(0);
		return count.toString();
	};

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param ter
	 */
	@Transactional
	public void updateSave(Terminal ter) {
		entityManager.merge(ter);
	};

	/**
	 * 根据ID得到要修改的值
	 * 
	 * @param id
	 * @return
	 */
	public Terminal findbyid(int id) {
		Terminal ter = entityManager.find(Terminal.class, id);
		return ter;
	}

	/**
	 * 得到所有终端的编号，名称
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Terminal> queryCode() {
		String sql = "from Terminal";
		Query query = entityManager.createQuery(sql);
		List<Terminal> list = query.getResultList();
		return list;
	};
}
