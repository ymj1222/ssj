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
	 * ���
	 */
	private String code;
	/**
	 * ����
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
	 * �ϴ�ʱ��
	 */
	private String video;
	
	/**
	 * ���ݴ�����
	 */
	private String creator;
	/**
	 * �����޸���
	 */
	private String updator;
	/**
	 * ���ݴ���ʱ��
	 */
	@Column(name = "create_time")
	private String createTime;
	/**
	 * �����޸�ʱ��
	 */
	@Column(name = "last_update_time")
	private String updateTime;
	@OneToOne(cascade = CascadeType.REMOVE) // JPAע�ͣ� һ��һ ��ϵ
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
