package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_logistics")
@Entity
public class Logistics {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * addr
	 */
	private String addr;
	/**
	 * 上传时间
	 */
	private String phone;
	/**
	 * 数据创建人
	 */
	private String creator;
	/**
	 * 数据修改人
	 */
	@Column(name="last_updator")
	private String lastUpdator;
	/**
	 * 数据创建时间
	 */
	@Column(name="create_time")
	private String createTime;
	/**
	 * 数据修改时间
	 */
	@Column(name="last_update_time")
	private String lastUpdateTime;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(String lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "Logistics [id=" + id + ", code=" + code + ", name=" + name + ", addr=" + addr + ", phone=" + phone
				+ ", creator=" + creator + ", updator=" + lastUpdator + ", createTime=" + createTime + ", updateTime="
				+ lastUpdateTime + ", getId()=" + getId() + ", getCode()=" + getCode() + ", getName()=" + getName()
				+ ", getAddr()=" + getAddr() + ", getPhone()=" + getPhone() + ", getCreator()=" + getCreator()
				+ ", getUpdator()=" + getLastUpdator() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()="
				+ getLastUpdateTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
