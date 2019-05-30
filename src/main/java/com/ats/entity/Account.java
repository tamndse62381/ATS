package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String accessToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String email;

	private String fullName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModify;

	private String password;

	private String status;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="RoleId")
	private Role role;

	//bi-directional many-to-one association to Cv
	@OneToMany(mappedBy="account")
	private List<Cv> cvs;

	//bi-directional many-to-one association to Employer
	@OneToMany(mappedBy="account")
	private List<Employer> employers;

	//bi-directional many-to-one association to Jobseekerlikejob
	@OneToMany(mappedBy="account")
	private List<Jobseekerlikejob> jobseekerlikejobs;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Cv> getCvs() {
		return this.cvs;
	}

	public void setCvs(List<Cv> cvs) {
		this.cvs = cvs;
	}

	public Cv addCv(Cv cv) {
		getCvs().add(cv);
		cv.setAccount(this);

		return cv;
	}

	public Cv removeCv(Cv cv) {
		getCvs().remove(cv);
		cv.setAccount(null);

		return cv;
	}

	public List<Employer> getEmployers() {
		return this.employers;
	}

	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}

	public Employer addEmployer(Employer employer) {
		getEmployers().add(employer);
		employer.setAccount(this);

		return employer;
	}

	public Employer removeEmployer(Employer employer) {
		getEmployers().remove(employer);
		employer.setAccount(null);

		return employer;
	}

	public List<Jobseekerlikejob> getJobseekerlikejobs() {
		return this.jobseekerlikejobs;
	}

	public void setJobseekerlikejobs(List<Jobseekerlikejob> jobseekerlikejobs) {
		this.jobseekerlikejobs = jobseekerlikejobs;
	}

	public Jobseekerlikejob addJobseekerlikejob(Jobseekerlikejob jobseekerlikejob) {
		getJobseekerlikejobs().add(jobseekerlikejob);
		jobseekerlikejob.setAccount(this);

		return jobseekerlikejob;
	}

	public Jobseekerlikejob removeJobseekerlikejob(Jobseekerlikejob jobseekerlikejob) {
		getJobseekerlikejobs().remove(jobseekerlikejob);
		jobseekerlikejob.setAccount(null);

		return jobseekerlikejob;
	}

}