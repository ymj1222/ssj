/**
 * 
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author Administrator
 * @date 2019��3��28�� TODO ���aƷ
 */
@Cacheable(true)
@Entity
@Table(name="productevaluation")
public class ProductEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="productCode")
	private Long productCode;
	private Byte type;
	private String context;
	@Column(name="createTime")
	private Date createTime;// ��������
	private String creator;// ������
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// �����޸ĕr�g
	@Column(name="lastUpdater")
	private String lastUpdater;// �����޸���
	@ManyToOne
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
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
		return "ProductEvaluation [id=" + id + ", productCode=" + productCode + ", type=" + type + ", context="
				+ context + ", createTime=" + createTime + ", creator=" + creator + ", lastUpdateTime=" + lastUpdateTime
				+ ", lastUpdater=" + lastUpdater + ", product=" + product + "]";
	}

	

}
