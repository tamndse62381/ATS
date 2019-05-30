package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the workexperience database table.
 * 
 */
@Entity
@NamedQuery(name="Workexperience.findAll", query="SELECT w FROM Workexperience w")
public class Workexperience implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String companyName;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	private String vacancyName;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	public Workexperience() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getVacancyName() {
		return this.vacancyName;
	}

	public void setVacancyName(String vacancyName) {
		this.vacancyName = vacancyName;
	}

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

}