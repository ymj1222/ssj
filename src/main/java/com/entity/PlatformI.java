package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="zj_platform_information")
@Entity
public class PlatformI {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String logo;
@Column(name="company_website")
private String companyWebsite;
private String qr;
private String tel;
private String code;

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
public String getCompanyWebsite() {
	return companyWebsite;
}
public void setCompanyWebsite(String companyWebsite) {
	this.companyWebsite = companyWebsite;
}
public String getQr() {
	return qr;
}
public void setQr(String qr) {
	this.qr = qr;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}

}
