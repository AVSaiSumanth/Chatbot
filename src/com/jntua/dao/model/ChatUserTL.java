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
public class ChatUserTL {
	@Id
	@GeneratedValue
	private Integer chatUserId;
	private String username;
	private String emailid;
	public Integer getChatUserId() {
		return chatUserId;
	}
	public void setChatUserId(Integer chatUserId) {
		this.chatUserId = chatUserId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	

}
