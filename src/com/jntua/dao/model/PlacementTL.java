package com.jntua.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
) 
public class PlacementTL {
	
	@Id
	@GeneratedValue
	private Integer placementId;
	
	private String courseName;
	private String name;
	private String regNo;
	private String cname;
	private String year;
	private Double ctc;
	public Double getCtc() {
		return ctc;
	}
	public void setCtc(Double ctc) {
		this.ctc = ctc;
	}
	public Integer getPlacementId() {
		return placementId;
	}
	public void setPlacementId(Integer placementId) {
		this.placementId = placementId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	

	

}
