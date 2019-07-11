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
	 * 根据id删除
	 * 
	 * @param id
	 */

	public void delete(String id) {
		Integer code = new Integer(id);
		serviceRecordDao.delete(code);
		shoppingGuideDao.delete(code);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<ShoppingGuide> select(Pageh pageh) {
		List<ShoppingGuide> list = shoppingGuideDao.select(pageh);
		return list;
	}

	/**
	 * 得到数据总页数
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
	 * 得到要修改的数据
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
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(ShoppingGuide shoppingGuide) {
		shoppingGuideDao.updateSave(shoppingGuide);
	}

	/**
	 * 当客服被抓去服务的时候，将在线状态改成忙线
	 */
	public void updatetypebusy(String code) {
		System.out.println(code);
		shoppingGuideDao.updatetypebusy(Integer.parseInt(code));
	}

	/**
	 * 当客服服务完成之后，或者上线的时候将状态改为在线
	 */
	public void updatetypeon(String code) {
		shoppingGuideDao.updatetypeon(Integer.parseInt(code));
	}

	/**
	 * 客服下线，将状态改为下线
	 */
	public void updatetypeoff(String code) {
		shoppingGuideDao.updatetypeoff(Integer.parseInt(code));
	}

	/**
	 * 上线
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
	 * 下线
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
