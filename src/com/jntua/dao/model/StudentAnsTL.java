package com.jntua.dao.model;

import java.sql.Timestamp;

public class StudentAnsTL {
	private Integer studentansid;
	private Integer qid;
	private String ans;
	private Integer studentId;
	private Timestamp createdon;
	private Integer sid;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getStudentansid() {
		return studentansid;
	}
	public void setStudentansid(Integer studentansid) {
		this.studentansid = studentansid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	

}
