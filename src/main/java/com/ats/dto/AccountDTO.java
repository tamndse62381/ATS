package com.ats.dto;

public class AccountDTO {
	int id;
	String username;
	String password;
	int typeId;
	byte enable;
	
	public AccountDTO() {
		super();
	}
	
	public AccountDTO(int id, String username, String password, int typeId, byte enable) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.typeId = typeId;
		this.enable = enable;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public byte isEnable() {
		return enable;
	}


	public void setEnable(byte enable) {
		this.enable = enable;
	}
	
	
	
}
