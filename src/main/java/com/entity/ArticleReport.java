package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "zj_article_report")
@Entity
public class ArticleReport {
//���¾ٱ�
	@Id // ���Ϊһ�������ֶ�
	@GeneratedValue(strategy = GenerationType.IDENTITY) // �������������ɲ���
	private Integer id;
	private String articleCode;// ���±��
	private String articleName;// ��������
	private String type;// �ٱ�����
	private String context;// �ٱ�����
	private String reportingDate;// �ٱ�����
	private String createTime;
	private String createor;
	private String lastUpdateTime;
	private String lastUpdater;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "article_id")
	private Article article;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
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

	@Override
	public String toString() {
		return "ArticleReport [id=" + id + ", articleCode=" + articleCode + ", articleName=" + articleName + ", type="
				+ type + ", context=" + context + ", reportingDate=" + reportingDate + ", createTime=" + createTime
				+ ", createor=" + createor + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater=" + lastUpdater
				+ ", article=" + article + "]";
	}

}
