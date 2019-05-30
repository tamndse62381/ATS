package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the apply database table.
 * 
 */
@Entity
@NamedQuery(name="Apply.findAll", query="SELECT a FROM Apply a")
public class Apply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dayApply;

	private String status;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="JobSeekerID")
	private Cv cv;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="JobID")
	private Job job;

	public Apply() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDayApply() {
		return this.dayApply;
	}

	public void setDayApply(Date dayApply) {
		this.dayApply = dayApply;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}