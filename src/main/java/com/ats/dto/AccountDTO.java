package com.ats.dto;

import java.io.Serializable;
import java.util.Date;

public class AccountDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String email;
	String password;
	String fullname;
	String status;
	Date createdDate;
	Date lastLogin;
	Date lastModify;
	int roleId;
	String accessToken;

	public AccountDTO() {
		super();
	}


	
	public AccountDTO(int id, String fullname, String email,int roleId, String accessToken) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleId = roleId;
		this.accessToken = accessToken;
	}



	public AccountDTO(int id,String email, String password, String fullname, String status, Date createdDate, Date lastLogin,
			Date lastModify, int roleId, String accessToken) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.status = status;
		this.createdDate = createdDate;
		this.lastLogin = lastLogin;
		this.lastModify = lastModify;
		this.roleId = roleId;
		this.accessToken = accessToken;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
}