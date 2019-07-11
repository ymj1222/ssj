package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 导购员表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "zj_shopping_guide")
public class ShoppingGuide {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 导购员编号
	 */
	private String code;
	/**
	 * 导购员名称
	 */
	private String name;
	/**
	 * 导购员性别
	 */
	private String sex;
	/**
	 * 导购员联系方式
	 */
	private String phone;
	/**
	 * 微信二维码
	 */
	private String WechatTwoDimensionalCode;
	/**
	 * 导购员照片
	 */
	private String photo;
	/**
	 * 状态（上线，下线）
	 */
	private Integer type;
	/**
	 * 爱好
	 */
	private String hobby;

	/**
	 * 数据创建人
	 */
	private String creator;
	/**
	 * 数据修改人
	 */
	private String updator;

	private Set<ServiceRecord> set = new HashSet<>();

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechatTwoDimensionalCode() {
		return WechatTwoDimensionalCode;
	}

	public void setWechatTwoDimensionalCode(String wechatTwoDimensionalCode) {
		WechatTwoDimensionalCode = wechatTwoDimensionalCode;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
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

	/**
	 * 数据创建时间
	 */
	private String createTime;
	/**
	 * 数据修改时间
	 */
	private String updateTime;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "shoppingGuide")
	public Set<ServiceRecord> getSet() {
		return set;
	}

	public void setSet(Set<ServiceRecord> set) {
		this.set = set;
	}

	@Override
	public String toString() {
		return "ShoppingGuide [id=" + id + ", code=" + code + ", name=" + name + ", sex=" + sex + ", phone=" + phone
				+ ", WechatTwoDimensionalCode=" + WechatTwoDimensionalCode + ", photo=" + photo + ", type=" + type
				+ ", hobby=" + hobby + ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
