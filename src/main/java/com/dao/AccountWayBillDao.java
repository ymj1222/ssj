package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountWayBill;
import com.entity.Addr;

@Repository
public class AccountWayBillDao {
	@PersistenceContext
	private EntityManager entityManager;
	public Addr findByAccount(String wayBillNo) {
		String jpql="select aw from AccountWayBill as aw where aw.wayBillNo=?1";
		 Query query =  entityManager.createQuery(jpql);
		 query.setParameter(1, wayBillNo);
		 return (Addr) query.getResultList().get(0); 
	}
	@Transactional
	public void deleteByAccount(String wayBillNo) {
		String jpql="DELETE from AccountWayBill where wayBillNo=?1";
		 Query query =  entityManager.createQuery(jpql);
		 query.setParameter(1, wayBillNo);
		 query.executeUpdate();
	}
/**
 * @param wayBillNo
 * @param account
 */
	@Transactional
public void addAccountWayBill(String wayBillNo,String account) {
	AccountWayBill aw=new AccountWayBill();
	aw.setAccount(account);
	aw.setWayBillNO(wayBillNo);
	entityManager.persist(aw);
}
	@Transactional
public void updateAccountWayBillNo(AccountWayBill aw) {
	String jpql="UPDATE account_waybill set waybill_no=?1 where account=?2";
	 Query query =  entityManager.createQuery(jpql);
	 query.setParameter(1, aw.getWayBillNO());
	 query.setParameter(2, aw.getAccount());
	 query.executeUpdate();
} 
}