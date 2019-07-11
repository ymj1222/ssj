package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息推送
 * 
 * @author 小疯子
 *
 */
@Entity
@Table(name = "zj_information_push")
public class InformationPush {
	/**
	 * 主键
	 */
	private Integer  id;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 推送消息
	 */
	private String datailed;
	/**
	 * 推送日期
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
	private String createTime;
	/**
	 * 数据修改时间
	 */
	private String updateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDatailed() {
		return datailed;
	}

	public void setDatailed(String datailed) {
		this.datailed = datailed;
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
		return "InformationPush [id=" + id + ", phone=" + phone + ", datailed=" + datailed + ", date=" + date
				+ ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

}
