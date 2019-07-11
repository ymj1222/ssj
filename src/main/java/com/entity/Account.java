package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="zj_account")
@Entity
public class Account  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String name;
private String code;
private String account;
private String password;
@Column(name="is_admin")
private String isAdmin;
private String state;
private String creator;
@Column(name="create_time")
private String createTime;
@Column(name="last_updator")
private String lastUpdator;
@Column(name="last_update_time")
private String lastUpdateTime;
@JoinTable(name="zj_account_auth",//中间表的名称
joinColumns={@JoinColumn(name="account",referencedColumnName="account")},//中间表PRODUCT_ID字段关联PRODUCT的ID
inverseJoinColumns={@JoinColumn(name="auth_code",referencedColumnName="code")})//中间表CATEGORY_ID字段关联CATEGORY的ID
@JsonIgnore
@ManyToMany
private List<Auth> auth = new ArrayList<Auth>();

public List<Auth> getAuth() {
	return auth;
}
public void setAuth(List<Auth> auth) {
	this.auth = auth;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getIsAdmin() {
	return isAdmin;
}
public void setIsAdmin(String isAdmin) {
	this.isAdmin = isAdmin;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getLastUpdator() {
	return lastUpdator;
}
public void setLastUpdator(String lastUpdator) {
	this.lastUpdator = lastUpdator;
}
public String getLastUpdateTime() {
	return lastUpdateTime;
}
public void setLastUpdateTime(String lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}
@Override
public String toString() {
	return "Account [id=" + id + ", name=" + name + ", code=" + code + ", account=" + account + ", password=" + password
			+ ", isAdmin=" + isAdmin + ", state=" + state + ", creator=" + creator + ", createTime=" + createTime
			+ ", lastUpdator=" + lastUpdator + ", lastUpdateTime=" + lastUpdateTime + "]";
}

}
