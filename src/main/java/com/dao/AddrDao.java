package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Addr;

@Repository
public class AddrDao {
	@Autowired
	WayBillDao w;
	@PersistenceContext
	private EntityManager entityManager;
	public List<Addr>findAll(){
		String jpql="select * from logistics_addr a";
		  Query query =  entityManager.createNativeQuery(jpql);
		 return query.getResultList();
	}
	public int queryNumber(String wayBillNo) {
		String jpql="select count(*) from logistics_addr as a where a.waybill_no=?1";
		  Query query =  entityManager.createNativeQuery(jpql);
		  query.setParameter(1, wayBillNo);
		  Long l= Long.valueOf(query.getResultList().get(0).toString());
			 return l.intValue();
	}
	public Addr findByCode(String code) {
		String jpql="select * from logistics_addr as a where waybill_no = ?1";
		Query query =  entityManager.createNativeQuery(jpql);
		query.setParameter(1, code);
		return (Addr) query.getResultList().get(0);
	}
	public Addr findCityCodeBywayBillNo(String wayBillNo) {
		String jpql=" select * from logistics_addr as a where a.waybill_no =?1 order by a.id desc";
		Query query =  entityManager.createNativeQuery(jpql);
		List<Object> list=query.setParameter(1, wayBillNo).setMaxResults(1).setFirstResult(0).getResultList();
		if(list.size()>0) { 
			Addr addr=new Addr();
			for (int i = 0; i < list.size(); i++) {
				Object[]obj=(Object[]) list.get(i);
				for (int j = 0; j < obj.length; j++) {
					addr.setCityCode(obj[1].toString());
					addr.setTime(obj[2].toString());
					addr.setW(w.findById(obj[4].toString()));
				}
			}
			return addr;
		}else {
			return null;
		}
	}
	@Transactional
	public void deleteByCode(String wayBillNo) {
		String jpql="DELETE from logistics_addr where waybill_no =?1";
		Query query =  entityManager.createNativeQuery(jpql);
		query.setParameter(1, wayBillNo);
		query.executeUpdate();
	}
	@Transactional
	public void addAddr(Addr addr) {
		  entityManager.persist(addr);
	}
	@Transactional
	public void update(Addr addr) {
		String jpql="update logistics_addr set time=?1,cityCode=?2 where waybill_no=?3";
		Query query =  entityManager.createQuery(jpql);
		query.setParameter(1, addr.getTime()).setParameter(2, addr.getCityCode()).setParameter(3,addr.getW().getWayBillNo()).executeUpdate();
	}
}
