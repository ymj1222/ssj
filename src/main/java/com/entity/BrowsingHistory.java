package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_browsing_history")
@Entity
public class BrowsingHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
	@Column(name="users_code")
private String usersCode;
private String url;
private String 	time;
private String code;

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}

public String getUsersCode() {
	return usersCode;
}
public void setUsersCode(String usersCode) {
	this.usersCode = usersCode;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
