package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String fullName;

	private String shortName;

	//bi-directional many-to-one association to Cv
	@OneToMany(mappedBy="city")
	private List<Cv> cvs;

	//bi-directional many-to-one association to Employer
	@OneToMany(mappedBy="city")
	private List<Employer> employers;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="city")
	private List<Job> jobs;

	public City() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

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
//		cv.setCity(this);
//
//		return cv;
//	}
//
//	public Cv removeCv(Cv cv) {
//		getCvs().remove(cv);
//		cv.setCity(null);
//
//		return cv;
//	}
//
//	public List<Employer> getEmployers() {
//		return this.employers;
//	}
//
//	public void setEmployers(List<Employer> employers) {
//		this.employers = employers;
//	}
//
//	public Employer addEmployer(Employer employer) {
//		getEmployers().add(employer);
//		employer.setCity(this);
//
//		return employer;
//	}
//
//	public Employer removeEmployer(Employer employer) {
//		getEmployers().remove(employer);
//		employer.setCity(null);
//
//		return employer;
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
//		job.setCity(this);
//
//		return job;
//	}
//
//	public Job removeJob(Job job) {
//		getJobs().remove(job);
//		job.setCity(null);
//
//		return job;
//	}
}