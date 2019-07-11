package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="zj_advertisementClick")
@Entity
public class AdvertisementClick{

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id;
	/**
	 * ���code
	 */
	@Column(name="advertisement_code")
	private String advertisementCode;
	/**
	 * �������
	 */
	@Column(name="cliclk_frequency")
	private Integer cliclkFrequency;
	
	/**
	 * ���ݴ�����
	 */
	private String creator;
	/**
	 * �����޸���
	 */
	private String updator;
	/**
	 * ���ݴ���ʱ��
	 */
	@Column(name="create_time")
	private String createTime;
	/**
	 * �����޸�ʱ��
	 */
	@Column(name="last_update_time")
	private String updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdvertisementCode() {
		return advertisementCode;
	}
	public void setAdvertisementCode(String advertisementCode) {
		this.advertisementCode = advertisementCode;
	}
	public Integer getCliclkFrequency() {
		return cliclkFrequency;
	}
	public void setCliclkFrequency(Integer cliclkFrequency) {
		this.cliclkFrequency = cliclkFrequency;
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
	@Override
	public String toString() {
		return "AdvertisementClick [id=" + id + ", advertisementCode=" + advertisementCode + ", cliclkFrequency="
				+ cliclkFrequency + ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
	
}
