package com.jntua.dao.model;

public class StaffSubjectTL {
	private Integer staffSubjectId;
	private Integer subjectId;
	private Integer staffId;
	private String status;
	public Integer getStaffSubjectId() {
		return staffSubjectId;
	}
	public void setStaffSubjectId(Integer staffSubjectId) {
		this.staffSubjectId = staffSubjectId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
