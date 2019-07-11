package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_users")
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
private String code;
private String name;
private String phone;
private String wechat;
private String sex;
private String city;
private String state;
private String account;

public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
private int age;
@Column(name="level_mark")
private int levelMark;
@Column(name="gold_coin")
private int goldCoin;
private int integral;
private String creator;
@Column(name="create_time")
private String createTime;
@Column(name="last_updator")
private String lastUpdator;
@Column(name="last_update_time")
private String lastUpdateTime;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getWechat() {
	return wechat;
}
public void setWechat(String wechat) {
	this.wechat = wechat;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public int getLevelMark() {
	return levelMark;
}
public void setLevelMark(int levelMark) {
	this.levelMark = levelMark;
}
public int getGoldCoin() {
	return goldCoin;
}
public void setGoldCoin(int goldCoin) {
	this.goldCoin = goldCoin;
}
public int getIntegral() {
	return integral;
}
public void setIntegral(int integral) {
	this.integral = integral;
}

}
