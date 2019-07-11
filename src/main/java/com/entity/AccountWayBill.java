package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="account_waybill")
@Entity
public class AccountWayBill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
private String account;
@Column(name="waybill_no")
private String wayBillNO;
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getWayBillNO() {
	return wayBillNO;
}
public void setWayBillNO(String wayBillNO) {
	this.wayBillNO = wayBillNO;
}

}
