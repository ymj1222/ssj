/**
 * 
 */
package com.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Administrator
 * @date 2019��3��26�� TODO
 */
@Cacheable(true)
@Entity
public class WarehousingAndOut {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long productCode;
	private Integer type;
	private Date date;
	private Integer amount;
	private Long ordersCode;
	private Date createTime;// ��������
	private String creator;// ������
	private Date lastUpdateTime;// �����޸ĕr�g
	private String lastUpdater;// �����޸���
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
