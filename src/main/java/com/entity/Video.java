package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="zj_video")
@Entity
public class Video{

	/**
	 * 
	 */
	@GeneratedValue
	@Id
	private Integer id;
	/**
	 * 商品编号
	 */
	@Column(name="product_code")
	private String productCode;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * url
	 */
	private String url;
	/**
	 * 上传时间
	 */
	private String date;
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
	@Column(name="create_time")
	private String createTime;
	/**
	 * 数据修改时间
	 */
	@Column(name="last_update_time")
	private String updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		return "Photo [id=" + id + ", productCode=" + productCode + ", code=" + code + ", name=" + name + ", url=" + url
				+ ", date=" + date + ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
	
}
