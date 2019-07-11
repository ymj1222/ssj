package com.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="logistics_waybill")
@Entity
public class WayBill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	@Column(name="waybill_no")
    private String wayBillNo;
    private Integer number;
    private String unit;
    @Column(name="start_city")
    private String startCity;
    @Column(name="end_city")
    private String endCity;
    @Column(name="r_name")
    private String rName;
    @Column(name="r_phone")
    private String rPhone;
    @Column(name="r_addr")
    private String rAddr;
    @JsonIgnore
    @OneToMany(mappedBy="w",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
    private Set<Addr> order = new HashSet<>(); 
    
    public Set<Addr> getOrder() {
		return order;
	}
	public void setOrder(Set<Addr> order) {
		this.order = order;
	}

	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity == null ? null : endCity.trim();
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone == null ? null : rPhone.trim();
    }

    public String getrAddr() {
        return rAddr;
    }

    public void setrAddr(String rAddr) {
        this.rAddr = rAddr == null ? null : rAddr.trim();
    }
	@Override
	public String toString() {
		return "WayBill [id=" + id + ", wayBillNo=" + wayBillNo + ", number=" + number + ", unit=" + unit
				+ ", startCity=" + startCity + ", endCity=" + endCity + ", rName=" + rName + ", rPhone=" + rPhone
				+ ", rAddr=" + rAddr + ", order=" + order + "]";
	}
    
}