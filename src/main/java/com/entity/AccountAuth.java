package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="zj_account_auth")
@Entity
public class AccountAuth {
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
@Column(name="auth_code")
private String authCode;

public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getAuthCode() {
	return authCode;
}
public void setAuthCode(String authCode) {
	this.authCode = authCode;
}

}
