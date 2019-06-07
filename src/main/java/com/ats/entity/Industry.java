package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the industry database table.
 * 
 */
@Entity
@NamedQuery(name="Industry.findAll", query="SELECT i FROM Industry i")
public class Industry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Companyindustry
	@OneToMany(mappedBy="industry")
	//@JsonIgnore
	private transient List<Companyindustry> companyindustries;

	//bi-directional many-to-one association to Cv
	@OneToMany(mappedBy="industry")
	//@JsonIgnore
	private transient List<Cv> cvs;

	public Industry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Companyindustry> getCompanyindustries() {
//		return this.companyindustries;
//	}
//
//	public void setCompanyindustries(List<Companyindustry> companyindustries) {
//		this.companyindustries = companyindustries;
//	}
//
//	public Companyindustry addCompanyindustry(Companyindustry companyindustry) {
//		getCompanyindustries().add(companyindustry);
//		companyindustry.setIndustry(this);
//
//		return companyindustry;
//	}
//
//	public Companyindustry removeCompanyindustry(Companyindustry companyindustry) {
//		getCompanyindustries().remove(companyindustry);
//		companyindustry.setIndustry(null);
//
//		return companyindustry;
//	}

//	public List<Cv> getCvs() {
//		return this.cvs;
//	}
//
//	public void setCvs(List<Cv> cvs) {
//		this.cvs = cvs;
//	}
//
//	public Cv addCv(Cv cv) {
//		getCvs().add(cv);
//		cv.setIndustry(this);
//
//		return cv;
//	}
//
//	public Cv removeCv(Cv cv) {
//		getCvs().remove(cv);
//		cv.setIndustry(null);
//
//		return cv;
//	}

}