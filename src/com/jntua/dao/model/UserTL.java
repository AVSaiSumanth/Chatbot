package com.jntua.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_tl")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
) 
public class UserTL {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	@Override
	public String toString() {
		return "Faculty [ DESIGNATION="
				+ desg + ", Faculty Name="
				+ firstName +  lastName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + "]";
	}
	@Column(name="dept_id")
	private Integer deptId;
	@Column(name="user_type")
	private String desg;
	@Column(name="faculty_code")
	private String facultlyId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="mobile_no")
	private String mobileNo;
	@Column(name="email_id")
	private String emailId;

	private String userName;
	private String password;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getFacultlyId() {
		return facultlyId;
	}
	public void setFacultlyId(String facultlyId) {
		this.facultlyId = facultlyId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
