package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ServiceRecordDao;
import com.dao.ShoppingGuideDao;
import com.entity.ShoppingGuide;
import com.util.Pageh;

@Service
public class ShoppingGuideService {
	@Autowired
	private ShoppingGuideDao shoppingGuideDao;
	@Autowired
	private ServiceRecordDao serviceRecordDao;

	public void add(ShoppingGuide shoppingGuide) {
		shoppingGuideDao.add(shoppingGuide);
	}

	/**
	 * ����idɾ��
	 * 
	 * @param id
	 */

	public void delete(String id) {
		Integer code = new Integer(id);
		serviceRecordDao.delete(code);
		shoppingGuideDao.delete(code);
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public List<ShoppingGuide> select(Pageh pageh) {
		List<ShoppingGuide> list = shoppingGuideDao.select(pageh);
		return list;
	}

	/**
	 * �õ�������ҳ��
	 */
	public Integer gettotal(Pageh pageh) {
		int pageCount = 0;
		String rowCounts = shoppingGuideDao.gettotal(pageh);
		int rowCount = Integer.parseInt(rowCounts);
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
	public ShoppingGuide updatequery(String id) {
		Integer code = new Integer(id);
		ShoppingGuide shoppingGuide = shoppingGuideDao.updatequery(code);
		return shoppingGuide;
	}

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(ShoppingGuide shoppingGuide) {
		shoppingGuideDao.updateSave(shoppingGuide);
	}

	/**
	 * ���ͷ���ץȥ�����ʱ�򣬽�����״̬�ĳ�æ��
	 */
	public void updatetypebusy(String code) {
		System.out.println(code);
		shoppingGuideDao.updatetypebusy(Integer.parseInt(code));
	}

	/**
	 * ���ͷ��������֮�󣬻������ߵ�ʱ��״̬��Ϊ����
	 */
	public void updatetypeon(String code) {
		shoppingGuideDao.updatetypeon(Integer.parseInt(code));
	}

	/**
	 * �ͷ����ߣ���״̬��Ϊ����
	 */
	public void updatetypeoff(String code) {
		shoppingGuideDao.updatetypeoff(Integer.parseInt(code));
	}

	/**
	 * ����
	 * @param code
	 */
	public void getidupdate(String code) {
		if(code!=null && !code.equals("")) {
			List<ShoppingGuide> list = shoppingGuideDao.queryAll();
			for (int i = 0; i < list.size(); i++) {
				if(code.equals(list.get(i).getCode())) {
					shoppingGuideDao.updatetypeon(list.get(i).getId());
				}
			}
		}
	}
	
	/**
	 * ����
	 * @param code
	 */
	public void getidupdatedown(String code) {
		if(code!=null && !code.equals("")) {
			List<ShoppingGuide> list = shoppingGuideDao.queryAll();
			for (int i = 0; i < list.size(); i++) {
				if(code.equals(list.get(i).getCode())) {
					shoppingGuideDao.updatetypeoff(list.get(i).getId());
				}
			}
		}
	}
}
