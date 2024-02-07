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
public class ActivityTL {
	@Id
	@GeneratedValue
	private Integer activityId;
	
	private String activityName;
	
	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityName="
				+ activityName + ", remarks=" + remarks + ", dateOfConducted="
				+ dateOfConducted + "]";
	}
	private String remarks;
	private String dateOfConducted;
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDateOfConducted() {
		return dateOfConducted;
	}
	public void setDateOfConducted(String dateOfConducted) {
		this.dateOfConducted = dateOfConducted;
	}
	
	

}
