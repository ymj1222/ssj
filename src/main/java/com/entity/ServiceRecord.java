package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 导购员服务记录表
 * 
 * @author 小疯子
 *
 */
@Entity
@Table(name = "zj_service_record")
public class ServiceRecord {

	private Integer id;
	/**
	 * 导购员编号
	 */
	
	private String ShoppingGuideCode;

	/**
	 * 导购员姓名
	 */
	private String ShoppingGuideName;
	/**
	 * 用户的编号
	 */
	private String UserCode;
	/**
	 * 用户的编号
	 */
	private String UserName;
	/**
	 * 开始时间
	 */
	private String StartTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 时长
	 */
	private String time;
	/**
	 * 满意度
	 */
	private String staisfaction;
	/**
	 * 描述
	 */
	private String detailed;

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
	private ShoppingGuide shoppingGuide;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getShoppingGuideCode() {
		return ShoppingGuideCode;
	}

	public void setShoppingGuideCode(String shoppingGuideCode) {
		this.ShoppingGuideCode = shoppingGuideCode;
	}

	
	public String getShoppingGuideName() {
		return ShoppingGuideName;
	}

	public void setShoppingGuideName(String shoppingGuideName) {
		this.ShoppingGuideName = shoppingGuideName;
	}

	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		this.UserCode = userCode;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		this.StartTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStaisfaction() {
		return staisfaction;
	}

	public void setStaisfaction(String staisfaction) {
		this.staisfaction = staisfaction;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
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

	@ManyToOne
	public ShoppingGuide getShoppingGuide() {
		return shoppingGuide;
	}

	public void setShoppingGuide(ShoppingGuide shoppingGuide) {
		this.shoppingGuide = shoppingGuide;
	}

	@Override
	public String toString() {
		return "ServiceRecord [id=" + id + ", ShoppingGuideCode=" + ShoppingGuideCode + ", ShoppingGuideName="
				+ ShoppingGuideName + ", UserCode=" + UserCode + ", UserName=" + UserName + ", StartTime=" + StartTime
				+ ", endTime=" + endTime + ", time=" + time + ", staisfaction=" + staisfaction + ", detailed="
				+ detailed + ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
