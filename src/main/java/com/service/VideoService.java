package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VideoDao;
import com.entity.Video;
import com.util.Pageh;

@Service
public class VideoService {
	@Autowired
	private VideoDao videoDao;

	public void add(Video video) {
		videoDao.add(video);
	}

	/**
	 * 根据code删除
	 * 
	 * @param code
	 */
	public void delete(String code) {
		videoDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Video> select(Pageh pageh) {
		List<Video> list = videoDao.getPersons(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		int rowCount = videoDao.gettotal(pageh);
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
	public Video updatequery(String code) {
		Video ter = videoDao.upquery(code);
		return ter;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Video video) {
		videoDao.update(video);
	}
	
	public List<Video> getUrl() {
		List<Video> list = videoDao.getUrl();
		return list;
	}

}
