package com.service;

import java.util.List;

import com.entity.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.AdvertisementClickDao;
import com.entity.AdvertisementClick;
import com.util.Pageh;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertisementClickService {
	@Autowired
	private AdvertisementClickDao advertisementClickDao;

	public void add(AdvertisementClick advertisementClick) {
		advertisementClickDao.save(advertisementClick);
	}




	/**
	 * 得到要修改的数据
	 * 
	 * @param code
	 * @return
	 */
	public AdvertisementClick updatequery(String code) {
		AdvertisementClick ter = advertisementClickDao.getByAdvertisementCode(code);
		return ter;
	}
	/**
	 * 修改数据
	 * 
	 * @param advertisementClick
	 */
	@Transactional
	public void update(AdvertisementClick advertisementClick) {
		advertisementClickDao.updateAdvertisement(advertisementClick.getCliclkFrequency(),advertisementClick.getAdvertisementCode());
	}

    public Page<AdvertisementClick> queryAll(String name, Pageable pageable){
        Page<AdvertisementClick> page = null;
        if(null !=name) {
            page=advertisementClickDao.findByAdvertisementCodeContaining(name,pageable);
        }else{
            page=advertisementClickDao.findAll(pageable);
        }
        return page;
    }
}
