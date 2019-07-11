/**
 * 
 */
package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.criteria.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Administrator
 * @date 2019年3月26日 TODO
 */
@Cacheable(true)
@Entity
public class Warehouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long productCode;
	private Integer amount;
	private Date createTime;// 創立日期
	private String creator;// 創建人
	private Date lastUpdateTime;// 最後修改時間
	private String lastUpdater;// 最後修改人
	private Integer amountWarning;
	private String productName;
	@OneToOne
	private Product product;
	@JsonIgnore
	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<WarehousingAndOut> wao = new HashSet<>(); // 单向,双向

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public Integer getAmountWarning() {
		return amountWarning;
	}

	public void setAmountWarning(Integer amountWarning) {
		this.amountWarning = amountWarning;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<WarehousingAndOut> getWao() {
		return wao;
	}

	public void setWao(Set<WarehousingAndOut> wao) {
		this.wao = wao;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", productCode=" + productCode + ", amount=" + amount + ", createTime="
				+ createTime + ", creator=" + creator + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater="
				+ lastUpdater + ", amountWarning=" + amountWarning + ", productName=" + productName + "]";
	}

}
