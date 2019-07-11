package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TerminalDao;
import com.entity.Terminal;
import com.util.Pageh;

@Service
public class TerminalService {
	@Autowired
	private TerminalDao terminalDao;

	public void add(Terminal ter) {
		terminalDao.add(ter);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
		Integer code = new Integer(id);
		terminalDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Terminal> select(Pageh pageh) {
		List<Terminal> list = terminalDao.select(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		Integer pageCount = 0;
		String rowCounts = terminalDao.gettotal(pageh);
		Integer rowCount = Integer.parseInt(rowCounts);
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
	public Terminal updatequery(String id) {
		Integer code = new Integer(id);
		Terminal ter = terminalDao.findbyid(code);
		return ter;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Terminal meetroom) {
		terminalDao.updateSave(meetroom);
	}

	/**
	 * 得到所有终端的code
	 * 
	 * @return
	 */
	public List<Terminal> queryCode() {
		List<Terminal> list = terminalDao.queryCode();
		return list;
	}
}
