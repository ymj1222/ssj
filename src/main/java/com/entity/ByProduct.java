/**
 * 
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Administrator
 * @date 2019年3月28日 TODO 副產品
 */
@Cacheable(true)
@Entity
public class ByProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long productCode;
	private Float goldCoin; // 金幣
	private Float integral;// 經驗值
	private Date createTime;// 創立日期
	private String creator;// 創建人
	private Date lastUpdateTime;// 最後修改時間
	private String lastUpdater;// 最後修改人
	@OneToOne
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public Float getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(Float float1) {
		this.goldCoin = float1;
	}

	public Float getIntegral() {
		return integral;
	}

	public void setIntegral(Float float1) {
		this.integral = float1;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ByProduct [id=" + id + ", productCode=" + productCode + ", goldCoin=" + goldCoin + ", integral="
				+ integral + ", createTime=" + createTime + ", creator=" + creator + ", lastUpdateTime="
				+ lastUpdateTime + ", lastUpdater=" + lastUpdater + ", product=" + product + "]";
	}

}
