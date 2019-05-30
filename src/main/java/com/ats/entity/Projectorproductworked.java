package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the projectorproductworked database table.
 * 
 */
@Entity
@NamedQuery(name="Projectorproductworked.findAll", query="SELECT p FROM Projectorproductworked p")
public class Projectorproductworked implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	private String skillUsed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	private String vacancyName;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	public Projectorproductworked() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSkillUsed() {
		return this.skillUsed;
	}

	public void setSkillUsed(String skillUsed) {
		this.skillUsed = skillUsed;
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