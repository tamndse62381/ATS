package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Lob
	private String description;

	private String email;

	private int industryID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModify;

	@Lob
	private String logoImg;

	private String nameCompany;

	private String telephoneNumber;

	//bi-directional many-to-one association to Employer
	@ManyToOne
	@JoinColumn(name="EmployerID")
	private Employer employer;

	//bi-directional many-to-one association to Companyindustry
	@OneToMany(mappedBy="company")
	private List<Companyindustry> companyindustries;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="company")
	private List<Job> jobs;

	public Company() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIndustryID() {
		return this.industryID;
	}

	public void setIndustryID(int industryID) {
		this.industryID = industryID;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getLogoImg() {
		return this.logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getNameCompany() {
		return this.nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
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
//		companyindustry.setCompany(this);
//
//		return companyindustry;
//	}
//
//	public Companyindustry removeCompanyindustry(Companyindustry companyindustry) {
//		getCompanyindustries().remove(companyindustry);
//		companyindustry.setCompany(null);
//
//		return companyindustry;
//	}
//
//	public List<Job> getJobs() {
//		return this.jobs;
//	}
//
//	public void setJobs(List<Job> jobs) {
//		this.jobs = jobs;
//	}
//
//	public Job addJob(Job job) {
//		getJobs().add(job);
//		job.setCompany(this);
//
//		return job;
//	}
//
//	public Job removeJob(Job job) {
//		getJobs().remove(job);
//		job.setCompany(null);
//
//		return job;
//	}

}