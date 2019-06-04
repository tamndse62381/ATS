package com.ats.dto;

public class CompanyDTO {
	private int id; 
	private String nameCompany;
	private String logoImg; 
	
	public CompanyDTO(int id, String nameCompany, String logoImg) {
		super();
		this.id = id;
		this.nameCompany = nameCompany;
		this.logoImg = logoImg;
	}
	
	public CompanyDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	} 
}
