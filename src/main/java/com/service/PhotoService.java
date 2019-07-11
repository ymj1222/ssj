package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PhotoDao;
import com.entity.Photo;
import com.util.Pageh;

@Service
public class PhotoService {
	@Autowired
	private PhotoDao photoDao;

	public void add(Photo photo) {
		photoDao.add(photo);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String code) {
		photoDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Photo> select(Pageh pageh) {
		List<Photo> list = photoDao.getPersons(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		int rowCount = photoDao.gettotal(pageh);
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
	public Photo updatequery(String code) {
		Photo ter = photoDao.upquery(code);
		return ter;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Photo photo) {
		photoDao.update(photo);
	}
	public List<Photo> geturl() {
		List<Photo> list = photoDao.getUrl();
		return list;
	}
	public Photo getproductCode(String code) {
		List<Photo> ter = photoDao.getProduct(code);
		Photo photo = ter.get(0);
		return photo;
	}

	public List<Photo> getphotoUrl(String productCode) {
		List<Photo> list = photoDao.getphotoUrl(productCode);
		return list;
	}
	public Photo queryName(String url) {
		List<Photo> ter = photoDao.getProduct(url);
		Photo photo = ter.get(0);
		return photo;
	}
}
