package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the skillincv database table.
 * 
 */
@Entity
@NamedQuery(name="Skillincv.findAll", query="SELECT s FROM Skillincv s")
public class Skillincv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="SkillID")
	private Skill skill;

	public Skillincv() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}