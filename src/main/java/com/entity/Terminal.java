package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "zj_terminal")
public class Terminal {

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
	 * 联系方式
	 */
	private String phone;
	/**
	 * 地址
	 */
	private String addr;
	/**
	 * 数据创建人
	 */
	private String creator;
	/**
	 * 数据修改人
	 */
	private String updator;
	/**
	 * 数据创建时间
	 */
	private String createTime;
	/**
	 * 数据修改时间
	 */
	private String updateTime;

	private Set<Agent> agentSet = new HashSet<>();

	private Set<Advertisement> AdvertisementSet = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "terminal") // ,
	public Set<Agent> getAgentSet() {
		return agentSet;
	}

	public void setAgentSet(Set<Agent> agentSet) {
		this.agentSet = agentSet;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "terminaal")
	public Set<Advertisement> getAdvertisementSet() {
		return AdvertisementSet;
	}

	public void setAdvertisementSet(Set<Advertisement> advertisementSet) {
		AdvertisementSet = advertisementSet;
	}

	@Override
	public String toString() {
		return "Terminal [id=" + id + ", code=" + code + ", name=" + name + ", phone=" + phone + ", addr=" + addr
				+ ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

}
