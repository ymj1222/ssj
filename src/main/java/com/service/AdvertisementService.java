package com.service;

import com.dao.AdvertisementClickDao;
import com.dao.AdvertisementDao;
import com.entity.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementDao advertisementDao;
    @Autowired
    private AdvertisementClickDao advertisementClickDao;
    public void add(Advertisement advertisement) {
        advertisementDao.save(advertisement);
    }

    /**
     * 根据id删除
     *
     * @param code
     */
    @Transactional
    public void delete(String code) {
        advertisementDao.deleteByCode(code);
        advertisementClickDao.deleteByAdvertisementCode(code);
    }



    /**
     * 得到要修改的数据
     *
     * @param code
     * @return
     */
    public Advertisement updatequery(String code) {
        Advertisement te = advertisementDao.getByCode(code);
        return te;
    }

    /**
     * 修改数据
     *
     * @param advertisement
     */
    public void update(Advertisement advertisement) {
        advertisementDao.save(advertisement);
    }

    public Advertisement getquery(String photo) {
        Advertisement te = advertisementDao.getByPhoto(photo);
        return te;
    }

    public List<Advertisement> queryl() {
        List<Advertisement> list = advertisementDao.getOrderbyid();
        return list;
    }
    public Page<Advertisement> queryAl(String name, Pageable pageable){
        Page<Advertisement> page = null;
        if(null !=name) {
            page=advertisementDao.findByTerminalNameContaining(name, pageable);
        }else{
            page=advertisementDao.findAll(pageable);
        }
        return page;
    }
}
