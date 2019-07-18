package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountWayBill;
import com.entity.Addr;

@Repository
public interface AccountWayBillDao extends JpaRepository<AccountWayBill, Integer> {
	AccountWayBill findByWayBillNO(String wayBillNo);
@Modifying
@Transactional
@Query("UPDATE AccountWayBill set waybill_no=?1 where account=?2")
AccountWayBill updateAccountWayBillNO(String waybillNo,String account);
}