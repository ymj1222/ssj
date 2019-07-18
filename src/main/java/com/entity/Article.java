package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="zj_article")
public class Article {
	//文章
	@Id //标记为一个主键字段
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长主键生成策略
private Integer id;
private String code;
private String name;
private String content;//内容
private String releaseTime;//发布时间
private String specialCode;//专题编号
private String specialName;
private int issue;//是否发布
private String createTime;
private String createor;
private String lastUpdateTime;
private String lastUpdater;
@JsonIgnore
@ManyToOne(cascade= CascadeType.REMOVE)
private Special special;
@JsonIgnore
@OneToMany(mappedBy="article",cascade={CascadeType.REMOVE},fetch=FetchType.EAGER)
private Set<ArticleReport>articleReport=new HashSet<>();
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
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(String releaseTime) {
	this.releaseTime = releaseTime;
}
public String getSpecialCode() {
	return specialCode;
}
public void setSpecialCode(String specialCode) {
	this.specialCode = specialCode;
}
public String getSpecialName() {
	return specialName;
}
public void setSpecialName(String specialName) {
	this.specialName = specialName;
}
public int getIssue() {
	return issue;
}
public void setIssue(int issue) {
	this.issue = issue;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getCreateor() {
	return createor;
}
public void setCreateor(String createor) {
	this.createor = createor;
}
public String getLastUpdateTime() {
	return lastUpdateTime;
}
public void setLastUpdateTime(String lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}
public String getLastUpdater() {
	return lastUpdater;
}
public void setLastUpdater(String lastUpdater) {
	this.lastUpdater = lastUpdater;
}
public Special getSpecial() {
	return special;
}
public void setSpecial(Special special) {
	this.special = special;
}
public Set<ArticleReport> getArticleReport() {
	return articleReport;
}
public void setArticleReport(Set<ArticleReport> articleReport) {
	this.articleReport = articleReport;
}
@Override
public String toString() {
	return "Article [id=" + id + ", code=" + code + ", name=" + name + ", content=" + content + ", releaseTime="
			+ releaseTime + ", specialCode=" + specialCode + ", specialName=" + specialName + ", issue=" + issue
			+ ", createTime=" + createTime + ", createor=" + createor + ", lastUpdateTime=" + lastUpdateTime
			+ ", lastUpdater=" + lastUpdater + ", articleReport=" + articleReport + "]";
}


}
