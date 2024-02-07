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
public class CollegeTL {

	@Id
	@GeneratedValue
	private Integer collegeId;
	private String collegeName;
	private String address;
	private String principal;
	private String vicePrincipal;
	private Integer noofaff;
	private String estaYear;
	private String vc;
	private String hostelManager;
	private String ce;
	public String getHostelManager() {
		return hostelManager;
	}
	public void setHostelManager(String hostelManager) {
		this.hostelManager = hostelManager;
	}
	public String getCe() {
		return ce;
	}
	public void setCe(String ce) {
		this.ce = ce;
	}
	public String getVc() {
		return vc;
	}
	public void setVc(String vc) {
		this.vc = vc;
	}
	public Integer getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getVicePrincipal() {
		return vicePrincipal;
	}
	public void setVicePrincipal(String vicePrincipal) {
		this.vicePrincipal = vicePrincipal;
	}
	public Integer getNoofaff() {
		return noofaff;
	}
	public void setNoofaff(Integer noofaff) {
		this.noofaff = noofaff;
	}
	public String getEstaYear() {
		return estaYear;
	}
	public void setEstaYear(String estaYear) {
		this.estaYear = estaYear;
	}
	
	


}
