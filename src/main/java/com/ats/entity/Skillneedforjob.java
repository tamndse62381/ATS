package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the skillneedforjob database table.
 * 
 */
@Entity
@NamedQuery(name="Skillneedforjob.findAll", query="SELECT s FROM Skillneedforjob s")
public class Skillneedforjob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="JobID")
	private Job job;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="SkillID")
	private Skill skill;

	public Skillneedforjob() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}