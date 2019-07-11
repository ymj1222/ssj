package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_account_role")
@Entity
public class AccountRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String account;
private String roleCode;
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}

}
