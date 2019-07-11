package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Administrator
 * @date 2019年3月25日 TODO 商品品牌實體
 */
@Cacheable(true)
@Entity
@Table(name="productband")
public class ProductBrand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主鍵
	private Long code;// 編號
	private String name;// 類型
	@Column(name="createTime")
	private Date createTime;// 創立日期
	private String creator;// 創建人
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// 最後修改時間
	@Column(name="lastUpdater")
	private String lastUpdater;// 最後修改人
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private Set<Product> product = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductBrand [id=" + id + ", code=" + code + ", name=" + name + ", createTime=" + createTime
				+ ", creator=" + creator + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater=" + lastUpdater + "]";
	}

}
