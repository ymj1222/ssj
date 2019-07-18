package com.dao;

import com.entity.Advertisement;
import com.entity.AdvertisementClick;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvertisementClickDao extends
        JpaRepository< AdvertisementClick, Integer>,
        JpaSpecificationExecutor< AdvertisementClick> {

    AdvertisementClick getByAdvertisementCode(String advertisementCode);

    @Modifying
    @Query("UPDATE AdvertisementClick p SET p.cliclkFrequency = :cliclkFrequency WHERE p.advertisementCode = :advertisementCode")
    void updateAdvertisement(@Param("cliclkFrequency") Integer cliclkFrequency, @Param("advertisementCode") String advertisementCode);

    Page<AdvertisementClick> findByAdvertisementCodeContaining(String name, Pageable pageable);

    @Modifying
    @Query(value = "delete from AdvertisementClick a where a.advertisementCode = :code")
    void deleteByAdvertisementCode(@Param("code") String code);
}
