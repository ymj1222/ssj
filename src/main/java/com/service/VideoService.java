package com.service;

import java.util.List;

import com.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dao.VideoDao;
import com.entity.Video;
import com.util.Pageh;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;

@Service
public class VideoService {
	@Autowired
	private VideoDao videoDao;

	public void add(Video video) {
		videoDao.save(video);
	}

	/**
	 * 根据code删除
	 * 
	 * @param code
	 */
	public void delete(String code) {
		Video v = videoDao.getByCode(code);
		videoDao.delete(v);
	}





	/**
	 * 得到要修改的数据
	 * 
	 * @param code
	 * @return
	 */
	public Video updatequery(String code) {
		Video ter = videoDao.getByCode(code);
		return ter;
	}

	/**
	 * 修改数据
	 * 
	 * @param video
	 */
	@Transactional
	public void update(Video video) {
		videoDao.update(video.getName(),video.getCode());
	}

    public Page<Video> queryAll(String name, Pageable pageable){
        Page<Video> page = null;
        if(null !=name) {
            page=videoDao.findByNameContaining(name, pageable);
        }else{
            page=videoDao.findAll(pageable);
        }
        return page;
    }
    public List<Video> getUrl() {
        List<Video> list = videoDao.getByCodeNotNull();
        return list;
    }
}
