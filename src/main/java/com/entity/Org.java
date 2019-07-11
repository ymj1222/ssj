package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_org")
@Entity
public class Org {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//
	private String code;//Ψһ�ĺ�̨�Զ�����
	private String name;//����
	private String state;//״̬��0��տ��ã�1�����ã�
	private String creator;//������
	@Column(name="create_time")
	private String createTime;//����ʱ��
	@Column(name="last_updator")
	private String lastUpdator;//����޸���
	@Column(name="last_update_time")
	private String lastUpdateTime;//����޸�ʱ��
	@Column(name="p_code")
	private String  pCode;
	@Column(name="manager_code")
	private String managerCode;
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
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
		return "Org [id=" + id + ", code=" + code + ", name=" + name + ", state=" + state + ", creator=" + creator
				+ ", createTime=" + createTime + ", lastUpdator=" + lastUpdator + ", lastUpdateTime=" + lastUpdateTime
				+ "]";
	}
	
}
