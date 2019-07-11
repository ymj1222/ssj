package com.entity;

import java.util.HashSet;
import java.util.List;
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
@Table(name = "zj_special")
public class Special {
//	专题
	@Id // 标记为一个主键字段
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长主键生成策略
	private Integer id;
	private String code;
	private String name;
	private String type;
	private String createTime;
	private String createor;
	private String lastUpdateTime;
	private String lastUpdater;
	@JsonIgnore
	@OneToMany(mappedBy = "special", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Article> article = new HashSet<>();

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Special [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + ", createTime="
				+ createTime + ", createor=" + createor + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater="
				+ lastUpdater + ", article=" + article + "]";
	};

}
