package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.AccountWayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Addr;

@Repository
public interface AddrDao extends JpaRepository<Addr, Integer> {
	@Query(value = "select count(*) from logistics_addr  where waybill_no=?1",nativeQuery = true)
	int countByWayBillNo(String waybillNo);
	Addr findByCityCode(String code);
	@Query(value = "select * from logistics_addr  where waybill_no=?1 order by id desc ",nativeQuery = true)
	List<Addr> findByWayBillNoOrderByIdDesc(String wayBillNo);
	@Query(value="select count(*) from logistics_addr  where waybill_no=?1",nativeQuery =true)
	Addr findByWayBillNo(Integer waybillNo);
    @Query("update Addr set time=?1,cityCode=?2 where waybill_no=?3")
    void update(String time,String cityCode,String waybillNo);
}
