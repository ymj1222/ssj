package com.service;

import java.util.List;

import com.dao.TerminalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdvertisementDao;
import com.entity.Advertisement;
import com.util.Pageh;

@Service
public class AdvertisementService {
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private TerminalDao terminal;
	public void add(Advertisement advertisement) {
		advertisementDao.add(advertisement);
	}

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */
	public void delete(String code) {
		advertisementDao.delete(code);
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public List<Advertisement> select(Pageh pageh) {
		List<Advertisement> list = advertisementDao.getPersons(pageh);
		return list;
	}

	/**
	 * �õ�������ҳ��
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		int rowCount = advertisementDao.gettotal(pageh);
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
	public Advertisement updatequery(String code) {
		Advertisement te = advertisementDao.upquery(code);
		return te;
	}

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Advertisement advertisement) {
		advertisementDao.update(advertisement);
	}
	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @return
	 */
	public Advertisement getquery(String photo) {
		Advertisement te = advertisementDao.getquery(photo);
		return te;
	}
	
	
	public List<Advertisement> queryl() {
		List<Advertisement> list = advertisementDao.getList();
		return list;
	}
	
}
