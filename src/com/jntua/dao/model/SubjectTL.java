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
public class SubjectTL {
	@Id
	@GeneratedValue
	private Integer subjectId;
	private String name;
	private String title;
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name
				+ ", title=" + title + ", courseId=" + courseId + ", qpattern="
				+ qpattern + ", semester=" + semester + "]";
	}
	private Integer courseId;
	private String qpattern;
	
	public String getQpattern() {
		return qpattern;
	}
	public void setQpattern(String qpattern) {
		this.qpattern = qpattern;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	private String semester;
	private String status;
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
