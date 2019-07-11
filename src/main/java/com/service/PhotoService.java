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
	 * ����idɾ��
	 * 
	 * @param id
	 */
	public void delete(String code) {
		photoDao.delete(code);
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public List<Photo> select(Pageh pageh) {
		List<Photo> list = photoDao.getPersons(pageh);
		return list;
	}

	/**
	 * �õ�������ҳ��
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
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Photo updatequery(String code) {
		Photo ter = photoDao.upquery(code);
		return ter;
	}

	/**
	 * �޸�����
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
