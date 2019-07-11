package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="zj_orders")
public class Orders {
	@Id //标记为一个主键字段
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长主键生成策略
private Integer id;
private String productCode;//商品编号
private String code;//编号
private String productName;//商品名字
private float price;//价格
private int amount;//商品数量
private String receivingAddress;//收货地址
private String phone;//收货人电话
private String consignee;//收货人
private String deliveryTime;//到货时间
private int isconfirmreceipt;//是否确认收货
private String receivingTime;//收货时间
private String usersCode;//顾客编号
private String createTime;
private String createor;
private String lastUpdateTime;
private String lastUpdater;
private String logisticsNumber;//运单编号
private int orderState;//订单状态
private String evaluation;//订单评价
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public WarehousingAndOut getWao() {
	return wao;
}
public void setWao(WarehousingAndOut wao) {
	this.wao = wao;
}
private String isOutOfStock;//是否发货
@ManyToOne
private Product product;
@OneToOne
private WarehousingAndOut wao;

@Override
public String toString() {
	return "Orders [id=" + id + ", productCode=" + productCode + ", code=" + code + ", productName=" + productName
			+ ", price=" + price + ", amount=" + amount + ", receivingAddress=" + receivingAddress + ", phone=" + phone
			+ ", consignee=" + consignee + ", deliveryTime=" + deliveryTime + ", isconfirmreceipt=" + isconfirmreceipt
			+ ", receivingTime=" + receivingTime + ", usersCode=" + usersCode + ", createTime=" + createTime
			+ ", createor=" + createor + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater=" + lastUpdater
			+ ", logisticsNumber=" + logisticsNumber + ", orderState=" + orderState + ", evaluation=" + evaluation
			+ ", isOutOfStock=" + isOutOfStock + ", product=" + product + ", wao=" + wao + "]";
}
public String getIsOutOfStock() {
	return isOutOfStock;
}
public void setIsOutOfStock(String isOutOfStock) {
	this.isOutOfStock = isOutOfStock;
}
public String getEvaluation() {
	return evaluation;
}
public void setEvaluation(String evaluation) {
	this.evaluation = evaluation;
}
public int getOrderState() {
	return orderState;
}
public void setOrderState(int orderState) {
	this.orderState = orderState;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getProductCode() {
	return productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public String getReceivingAddress() {
	return receivingAddress;
}
public void setReceivingAddress(String receivingAddress) {
	this.receivingAddress = receivingAddress;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getConsignee() {
	return consignee;
}
public void setConsignee(String consignee) {
	this.consignee = consignee;
}
public String getDeliveryTime() {
	return deliveryTime;
}
public void setDeliveryTime(String deliveryTime) {
	this.deliveryTime = deliveryTime;
}
public int getIsconfirmreceipt() {
	return isconfirmreceipt;
}
public void setIsconfirmreceipt(int isconfirmreceipt) {
	this.isconfirmreceipt = isconfirmreceipt;
}
public String getReceivingTime() {
	return receivingTime;
}
public void setReceivingTime(String receivingTime) {
	this.receivingTime = receivingTime;
}
public String getUsersCode() {
	return usersCode;
}
public void setUsersCode(String usersCode) {
	this.usersCode = usersCode;
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
public String getLogisticsNumber() {
	return logisticsNumber;
}
public void setLogisticsNumber(String logisticsNumber) {
	this.logisticsNumber = logisticsNumber;
}

}
