package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="zj_auth")
@Entity
public class Auth implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String code;
@JsonIgnore
@ManyToMany(mappedBy="auth", fetch = FetchType.LAZY)
private List<Account>account=new ArrayList<>();

public List<Account> getAccount() {	
	return account;
}
public void setAccount(List<Account> account) {
	this.account = account;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
private String name;
private String url;
@Column(name="is_base")
private String isBase;
@Column(name="menu_type")
private String menuType;
private String descri;
@Column(name="is_delete")
private String isDelete;
private String creator ;
@Column(name="create_time")
private String createTime;
@Column(name="last_updator")
private String lastUpdator;
@Column(name="last_update_time")
private String lastUpdateTime;
@Column(name="p_code")
private String pCode;

public String getpCode() {
	return pCode;
}
public void setpCode(String pCode) {
	this.pCode = pCode;
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
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getIsBase() {
	return isBase;
}
public void setIsBase(String isBase) {
	this.isBase = isBase;
}
public String getMenuType() {
	return menuType;
}
public void setMenuType(String menuType) {
	this.menuType = menuType;
}
public String getDescri() {
	return descri;
}
public void setDescri(String descri) {
	this.descri = descri;
}
public String getIsDelete() {
	return isDelete;
}
public void setIsDelete(String isDelete) {
	this.isDelete = isDelete;
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


}
