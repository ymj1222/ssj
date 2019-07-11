package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="logistics_addr_city")
@Entity
public class AddrCity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
private String name;
private String time;
private String distance;
private String howLong;

public String getDistance() {
	return distance;
}
public void setDistance(String distance) {
	this.distance = distance;
}
public String getHowLong() {
	return howLong;
}
public void setHowLong(String howLong) {
	this.howLong = howLong;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
}
