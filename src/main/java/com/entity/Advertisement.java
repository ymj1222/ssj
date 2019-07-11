package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "zj_advertisement")
@Entity
public class Advertisement {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	@Column(name = "terminal_name")
	private String terminalName;
	
	@Column(name = "terminal_code")
	private String terminalCode;
	/**
	 * url
	 */
	private String photo;
	/**
	 * 上传时间
	 */
	private String video;
	
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
	@Column(name = "create_time")
	private String createTime;
	/**
	 * 数据修改时间
	 */
	@Column(name = "last_update_time")
	private String updateTime;
	@OneToOne(cascade = CascadeType.REMOVE) // JPA注释： 一对一 关系
	@JoinColumn(name = "advertisementClick_id")
	private AdvertisementClick advertisementClick;
	@ManyToOne
	private Terminal terminaal;

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

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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

	public AdvertisementClick getAdvertisementClick() {
		return advertisementClick;
	}

	public void setAdvertisementClick(AdvertisementClick advertisementClick) {
		this.advertisementClick = advertisementClick;
	}

	public Terminal getTerminal() {
		return terminaal;
	}

	public void setTerminal(Terminal terminaal) {
		this.terminaal = terminaal;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", code=" + code + ", terminalName=" + terminalName + ", terminalCode="
				+ terminalCode + ", photo=" + photo + ", video=" + video + ", creator=" + creator + ", updator="
				+ updator + ", createTime=" + createTime + ", updateTime=" + updateTime + ", advertisementClick="
				+ advertisementClick + "]";
	}

	

	
}
