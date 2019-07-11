package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SpecialDao;
import com.entity.Special;
import com.util.Page;
import com.util.Pageh;

@Service
public class SpecialService {
	@Autowired
	private SpecialDao specialDao;
	/*@Autowired
	private ProductTypeDao pd;*/

	/**
	 * 找商品类型
	 * 
	 * @param id
	 */
	/*public List<ProductType> specialType(){
	List<ProductType>list=pd.selectType();
	return list;
	}*/
	
	
	public void add(Special special) {
		specialDao.add(special);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
	
		specialDao.delete(id);
	}

	/**
	 * 得到所有数据
	 * 
	 * @return
	 */
	public List<Special> select(Pageh page) {
		int pageNow = (page.getPageNow()-1)*page.getPageSize();
		page.setPageNow(pageNow);
		List<Special> list = specialDao.select(page);
		return list;
	}

	/**
	 * 得到数据总页数
	 */
	public Integer gettotal(int pageSize,String sname) {
		int pageCount = 0;
		int rowCount = specialDao.gettotal(sname);
		if ((rowCount % pageSize) == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

	/**
	 * 得到要修改的数据
	 * 
	 * @param id
	 * @return
	 */
	public Special updatequery(String code) {
		Special special = specialDao.updatequery(code);
		return special;
	}

	/**
	 * 修改数据
	 * 
	 * @param id
	 * @param meetroom
	 */
	public void update(Special special) {
		specialDao.updateSave(special);
	}
}
