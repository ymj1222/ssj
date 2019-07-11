package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工表
 * @author king
 *
 *yroa
 *下午7:16:53
 */
@Table(name="zj_staff")
@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;//id
private String code;//唯一的后台自动生成
private String name;//姓名
private String tel;
@Column(name="org_code")
private String orgCode;//入职部门编码
private String sex;//入职部门编码
private String birthday;
private String account;


public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
private Integer state;//员工状态
private String creator;//创建人
@Column(name="create_time")
private String createTime;//创建时间
@Column(name="last_updator")
private String lastUpdator;//最后修改人
@Column(name="last_update_time")
private String lastUpdateTime;//最后修改时间
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getOrgCode() {
	return orgCode;
}
public void setOrgCode(String orgCode) {
	this.orgCode = orgCode;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
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
	return "Staff [id=" + id + ", code=" + code + ", name=" + name + ", tel=" + tel + ", orgCode=" + orgCode
			+ ", state=" + state + ", creator=" + creator + ", createTime=" + createTime + ", lastUpdator="
			+ lastUpdator + ", lastUpdateTime=" + lastUpdateTime + "]";
}

}
