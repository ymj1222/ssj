package com.dao;

import com.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
 
public interface PhotoDao extends
        JpaRepository<Photo, Integer>,
        JpaSpecificationExecutor<Photo> {


    Photo getByCode(String code);
    //根据照片路径查询对应Photo
    List<Photo> getByUrl(String url);

    //@Query("SELECT t  FROM Photo t where t.productCode!= ''")
    List<Photo> getByProductCodeIsNotNull();

    List<Photo> getByProductCode(String productCode);
    Page<Photo> findByNameContaining(String name, Pageable pageable);

    @Modifying
    @Query(value = "update Photo p set p.name = :name where p.code = :code")
    void update(@Param("name") String name, @Param("code") String code);
}

