package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the companyindustry database table.
 * 
 */
@Entity
@NamedQuery(name="Companyindustry.findAll", query="SELECT c FROM Companyindustry c")
public class Companyindustry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Industry
	@ManyToOne
	@JoinColumn(name="IndustryID")
	private Industry industry;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="CompanyID")
	private Company company;

	public Companyindustry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Industry getIndustry() {
		return this.industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}