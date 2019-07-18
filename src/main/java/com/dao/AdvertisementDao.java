package com.dao;

import com.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementDao extends
        JpaRepository<Advertisement, Integer>,
        JpaSpecificationExecutor<Advertisement> {

    Advertisement getByCode(String code);

    @Modifying
    @Query(value = "delete from Advertisement a where a.code = :code")
    void deleteByCode(@Param("code") String code);

    Advertisement getByPhoto(String photo);

    @Query(value="select * from zj_advertisement  order by id desc limit 7 ",nativeQuery=true)
    List<Advertisement> getOrderbyid();

    Page<Advertisement> findByTerminalNameContaining(String name, Pageable pageable);

}
