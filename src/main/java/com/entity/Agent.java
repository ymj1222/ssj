package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 小疯子
 */
@Entity
@Table(name = "zj_agent")
public class Agent {

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 地址
	 */
	private String addr;

	/**
	 * 数据创建人
	 */
	private String creator;
	/**
	 * 数据修改人
	 */
	private String updator;
	/**
	 * 数据创建时间
	 */
	private String createTime;
	/**
	 * 数据修改时间
	 */
	private String updateTime;

	/**
	 * 终端编号
	 */
	private String terminalCode;

	private Terminal terminal;

	private Set<Product> product = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToOne
	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	@JsonIgnore
	@OneToMany(mappedBy="agent")
	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", code=" + code + ", name=" + name + ", phone=" + phone + ", addr=" + addr
				+ ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", terminalCode=" + terminalCode + "]";
	}

}
