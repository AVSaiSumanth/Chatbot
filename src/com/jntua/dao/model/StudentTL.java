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
public class StudentTL {

	@Id
	@GeneratedValue
	private Integer studentId;
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name
				+ ", emailId=" + emailId + ", mobileNo=" + mobileNo
				+ ", regNo=" + regNo + ", grade=" + grade + ", semester=" + semester
				+ "]";
	}
	private String name;
	private String emailId;
	private String mobileNo;
	private String regNo;
	private String password;
	private Integer courseId;
	private String grade;
	private Integer totalNoofClasses;
	private Integer totalNoofClassesAtten;
	
	
	public Integer getTotalNoofClasses() {
		return totalNoofClasses;
	}
	public void setTotalNoofClasses(Integer totalNoofClasses) {
		this.totalNoofClasses = totalNoofClasses;
	}
	public Integer getTotalNoofClassesAtten() {
		return totalNoofClassesAtten;
	}
	public void setTotalNoofClassesAtten(Integer totalNoofClassesAtten) {
		this.totalNoofClassesAtten = totalNoofClassesAtten;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	private String semester;
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
