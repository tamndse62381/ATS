package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employerlikecv database table.
 * 
 */
@Entity
@NamedQuery(name="Employerlikecv.findAll", query="SELECT e FROM Employerlikecv e")
public class Employerlikecv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private int isActive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifyDate;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	//bi-directional many-to-one association to Employer
	@ManyToOne
	@JoinColumn(name="EmployerID")
	private Employer employer;

	public Employerlikecv() {
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

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}