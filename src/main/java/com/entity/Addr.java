package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="logistics_addr")
@Entity
public class Addr {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    private String time;
    @Column(name="city_code")
    private String cityCode;
    @ManyToOne
    @JoinColumn(name="waybill_no")
private WayBill w;
	public String getCityCode() {
	return cityCode;
}

public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}

public WayBill getW() {
	return w;
}

public void setW(WayBill w) {
	this.w = w;
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "Addr [id=" + id  + ", time=" + time + ", addr=" + cityCode + "]";
	}

	

     
}