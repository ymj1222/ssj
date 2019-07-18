package com.service;

import com.dao.PhotoDao;
import com.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoService {
	@Autowired
	private PhotoDao photoDao;

	public void add(Photo photo) {
		photoDao.save(photo);
	}

	/**
	 * 根据id删除
	 * 
	 * @param code
	 */
	public void delete(String code) {
		Photo p = photoDao.getByCode(code);
		photoDao.delete(p);
	}




	/**
	 * 得到要修改的数据
	 * 
	 * @param code
	 * @return
	 */
	public Photo updatequery(String code) {
		Photo ter = photoDao.getByCode(code);
		return ter;
	}


	/**
	 * 修改数据
	 * 
	 * @param photo
	 */
	@Transactional
	public void update(Photo photo) {
		photoDao.update(photo.getName(),photo.getCode());
	}
	public List<Photo> geturl() {
		List<Photo> list = photoDao.getByProductCodeIsNotNull();
		return list;
	}
	public Photo getproductCode(String code) {
		List<Photo> ter = photoDao.getByUrl(code);
		Photo photo = ter.get(0);
		return photo;
	}

	public List<Photo> getphotoUrl(String productCode) {
		List<Photo> list = photoDao.getByProductCode(productCode);
		return list;
	}
	public Photo queryName(String url) {
		List<Photo> ter = photoDao.getByUrl(url);
		Photo photo = ter.get(0);
		return photo;
	}
    public Page<Photo> queryAll(String name, Pageable pageable){
        Page<Photo> page = null;
        if(null !=name) {
            page=photoDao.findByNameContaining(name, pageable);
        }else{
            page=photoDao.findAll(pageable);
        }
        return page;
    }
}
