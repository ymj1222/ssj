package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_option_log")
@Entity
public class OptionLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String operator;
@Column(name="operate_time")
private String operateTime;
@Column(name="operate_type")
private int operateType;
public String getOperator() {
	return operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}
public String getOperateTime() {
	return operateTime;
}
public void setOperateTime(String operateTime) {
	this.operateTime = operateTime;
}
public int getOperateType() {
	return operateType;
}
public void setOperateType(int operateType) {
	this.operateType = operateType;
}

}
