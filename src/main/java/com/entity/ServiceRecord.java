package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ����Ա�����¼��
 * 
 * @author С����
 *
 */
@Entity
@Table(name = "zj_service_record")
public class ServiceRecord {

	private Integer id;
	/**
	 * ����Ա���
	 */
	
	private String ShoppingGuideCode;

	/**
	 * ����Ա����
	 */
	private String ShoppingGuideName;
	/**
	 * �û��ı��
	 */
	private String UserCode;
	/**
	 * �û��ı��
	 */
	private String UserName;
	/**
	 * ��ʼʱ��
	 */
	private String StartTime;
	/**
	 * ����ʱ��
	 */
	private String endTime;
	/**
	 * ʱ��
	 */
	private String time;
	/**
	 * �����
	 */
	private String staisfaction;
	/**
	 * ����
	 */
	private String detailed;

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
	private String createTime;
	/**
	 * �����޸�ʱ��
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
