package com.dao;

import com.entity.Photo;
import com.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoDao extends
        JpaRepository<Video, Integer>,
        JpaSpecificationExecutor<Video> {


    Video getByCode(String code);

    List<Video> getByCodeNotNull();

    Page<Video> findByNameContaining(String name, Pageable pageable);

    @Modifying
    @Query(value = "update Video p set p.name = :name where p.code = :code")
    void update(@Param("name") String name, @Param("code") String code);
}
