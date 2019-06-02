package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the joblevel database table.
 * 
 */
@Entity
@NamedQuery(name="Joblevel.findAll", query="SELECT j FROM Joblevel j")
public class Joblevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String jobLevelName;

	//bi-directional many-to-one association to Employer
	@OneToMany(mappedBy="joblevel")
	private List<Employer> employers;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="joblevel")
	private List<Job> jobs;

	public Joblevel() {
	}

	public Joblevel(int id, String jobLevelName, List<Employer> employers, List<Job> jobs) {
		super();
		this.id = id;
		this.jobLevelName = jobLevelName;
		this.employers = employers;
		this.jobs = jobs;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobLevelName() {
		return this.jobLevelName;
	}

	public void setJobLevelName(String jobLevelName) {
		this.jobLevelName = jobLevelName;
	}

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
//		employer.setJoblevel(this);
//
//		return employer;
//	}
//
//	public Employer removeEmployer(Employer employer) {
//		getEmployers().remove(employer);
//		employer.setJoblevel(null);
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
//		job.setJoblevel(this);
//
//		return job;
//	}
//
//	public Job removeJob(Job job) {
//		getJobs().remove(job);
//		job.setJoblevel(null);
//
//		return job;
//	}

}