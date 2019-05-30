package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the jobseekerlikejob database table.
 * 
 */
@Entity
@NamedQuery(name="Jobseekerlikejob.findAll", query="SELECT j FROM Jobseekerlikejob j")
public class Jobseekerlikejob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private int isActive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifyDate;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="AccountID")
	private Account account;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="JobID")
	private Job job;

	public Jobseekerlikejob() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsActive() {
		return this.isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}