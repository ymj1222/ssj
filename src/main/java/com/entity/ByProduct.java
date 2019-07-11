/**
 * 
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author Administrator
 * @date 2019??3??28?? TODO ???a?
 */
@Cacheable(true)
@Entity
@Table(name="byproduct")
public class ByProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="productCode")
	private Long productCode;
	@Column(name="goldCoin")
	private Float goldCoin; // ????
	private Float integral;// ????
	@Column(name="createTime")
	private Date createTime;// ????????
	private String creator;// ??????
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// ???????r?g
	@Column(name="lastUpdater")
	private String lastUpdater;// ?????????
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
