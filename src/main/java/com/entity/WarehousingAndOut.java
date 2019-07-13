/**
 * 
 */
package com.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Administrator
 * @date 2019年3月26日 TODO
 */
@Cacheable(true)
@Entity

public class WarehousingAndOut {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="productCode")
	private Long productCode;
	private Integer type;
	private Date date;
	private Integer amount;
	@Column(name="ordersCode")
	private Long ordersCode;
	@Column(name="createTime")
	private Date createTime;// 創立日期
	private String creator;// 創建人
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// 最後修改時間
	@Column(name="lastUpdater")
	private String lastUpdater;// 最後修改人
	@OneToOne
	private Orders order;

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@ManyToOne
	private Warehouse warehouse;

	@ManyToOne
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Long getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(Long ordersCode) {
		this.ordersCode = ordersCode;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "WarehousingAndOut [id=" + id + ", productCode=" + productCode + ", type=" + type + ", date=" + date
				+ ", amount=" + amount + ", ordersCode=" + ordersCode + ", createTime=" + createTime + ", creator="
				+ creator + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater=" + lastUpdater + ", order=" + order
				+ ", warehouse=" + warehouse + ", product=" + product + "]";
	}

}
