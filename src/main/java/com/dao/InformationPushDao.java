package com.dao;

import com.entity.InformationPush;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationPushDao extends JpaRepository<InformationPush,Integer>, JpaSpecificationExecutor<InformationPush> {

}
