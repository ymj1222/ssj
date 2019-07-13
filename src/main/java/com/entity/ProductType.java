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
 * @date 2019��3��25�� TODO ��Ʒ��͌��w
 */
@Cacheable(true)
@Table(name="producttype")
@Entity
public class ProductType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ���I
	private Long code;// ��̖
	private Byte level;
	private String name;// ���
	@Column(name="createTime")
	private Date createTime;// ��������
	private String creator;// ������
	@Column(name="lastUpdateTime")
	private Date lastUpdateTime;// �����޸ĕr�g
	private String lastUpdater;// �����޸���
	@JsonIgnore
	@OneToMany(mappedBy = "producttype", fetch = FetchType.EAGER)
	private Set<Product> product = new HashSet<>();
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
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

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ProductType [id=" + id + ", code=" + code + ", level=" + level + ", name=" + name + ", createTime="
				+ createTime + ", creator=" + creator + ", lastUpdateTime=" + lastUpdateTime + ", lastUpdater="
				+ lastUpdater + "]";
	}

}
