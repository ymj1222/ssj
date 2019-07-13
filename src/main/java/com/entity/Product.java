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
 * @date 2019年3月25日 TODO 商品實體
 */
@Cacheable(true)
@Entity
@Table(name="products")

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主鍵
	private Long code;// 編號
	private String name;// 名稱
	private Float price;// 總價
	@Column(name="auditStatus")
	private Integer auditStatus;// 審批狀態
	private String induction;// 介紹
	private Date shelftime;// 上架時間
	private String size;// 大小
	private String color;// 顏色
	@Column(name="sellValue")
	private Float sellValue;// 銷售價
	@Column(name="marketValue")
	private Float marketValue;// 市場價
	@Column(name="agentCode")
	private Long agentCode;
	public Long getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(Long agentCode) {
		this.agentCode = agentCode;
	}

	@Transient
	@Column(name="TypeName")
	private String TypeName;
	@Transient
	@Column(name="brandName")
	private String brandName;
	@Transient
	@Column(name="agentName")
	private String agentName;

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	private Integer amount;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<Orders> order = new HashSet<>();
	@JsonIgnore
	@ManyToOne
	private Agent agent;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<ProductEvaluation> evaluation = new HashSet<>();
	@JsonIgnore
	@OneToOne
	private Warehouse warehouse;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<WarehousingAndOut> wao = new HashSet<>();

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Set<WarehousingAndOut> getWao() {
		return wao;
	}

	public void setWao(Set<WarehousingAndOut> wao) {
		this.wao = wao;
	}

	public ProductBrand getBrand() {
		return brand;
	}

	public Set<Orders> getOrder() {
		return order;
	}

	public void setOrder(Set<Orders> order) {
		this.order = order;
	}

	public void setBrand(ProductBrand brand) {
		this.brand = brand;
	}

	@JsonIgnore
	@ManyToOne
	private ProductBrand brand;
	@ManyToOne
	@JsonIgnore
	private ProductType producttype;

	public ProductType getProducttype() {
		return producttype;
	}

	public void setProducttype(ProductType producttype) {
		this.producttype = producttype;
	}

	public Integer getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	@Column(name="isEffective")
	private Integer isEffective;
	@Column(name="createTime")
	private Date createTime;// 創立日期
	private String creator;// 創建人
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// 最後修改時間
	@Column(name="lastUpdater")
	private String lastUpdater;// 最後修改人

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getSellValue() {
		return sellValue;
	}

	public void setSellValue(Float sellValue) {
		this.sellValue = sellValue;
	}

	public Float getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Float marketValue) {
		this.marketValue = marketValue;
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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getInduction() {
		return induction;
	}

	public Set<ProductEvaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Set<ProductEvaluation> evaluation) {
		this.evaluation = evaluation;
	}

	public void setInduction(String induction) {
		this.induction = induction;
	}

	public Date getShelftime() {
		return shelftime;
	}

	public void setShelftime(Date shelftime) {
		this.shelftime = shelftime;
	}

	private Long type;

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	private Long brandCode;

	public Long getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(Long brandCode) {
		this.brandCode = brandCode;
	}

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", price=" + price + ", auditStatus="
				+ auditStatus + ", induction=" + induction + ", shelftime=" + shelftime + ", size=" + size + ", color="
				+ color + ", sellValue=" + sellValue + ", marketValue=" + marketValue + ", agentCode=" + agentCode
				+ ", TypeName=" + TypeName + ", brandName=" + brandName + ", agentName=" + agentName + ", amount="
				+ amount + ", isEffective=" + isEffective + ", createTime=" + createTime + ", creator=" + creator
				+ ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater=" + lastUpdater + ", brandCode=" + brandCode
				+ "]";
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
