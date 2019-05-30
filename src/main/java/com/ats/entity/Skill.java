package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int skillLevelID;

	//bi-directional many-to-one association to Skillmaster
	@ManyToOne
	@JoinColumn(name="SkillMasterID")
	private Skillmaster skillmaster;

	//bi-directional many-to-one association to Skillincv
	@OneToMany(mappedBy="skill")
	private List<Skillincv> skillincvs;

	//bi-directional many-to-one association to Skillneedforjob
	@OneToMany(mappedBy="skill")
	private List<Skillneedforjob> skillneedforjobs;

	public Skill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSkillLevelID() {
		return this.skillLevelID;
	}

	public void setSkillLevelID(int skillLevelID) {
		this.skillLevelID = skillLevelID;
	}

	public Skillmaster getSkillmaster() {
		return this.skillmaster;
	}

	public void setSkillmaster(Skillmaster skillmaster) {
		this.skillmaster = skillmaster;
	}

	public List<Skillincv> getSkillincvs() {
		return this.skillincvs;
	}

	public void setSkillincvs(List<Skillincv> skillincvs) {
		this.skillincvs = skillincvs;
	}

	public Skillincv addSkillincv(Skillincv skillincv) {
		getSkillincvs().add(skillincv);
		skillincv.setSkill(this);

		return skillincv;
	}

	public Skillincv removeSkillincv(Skillincv skillincv) {
		getSkillincvs().remove(skillincv);
		skillincv.setSkill(null);

		return skillincv;
	}

	public List<Skillneedforjob> getSkillneedforjobs() {
		return this.skillneedforjobs;
	}

	public void setSkillneedforjobs(List<Skillneedforjob> skillneedforjobs) {
		this.skillneedforjobs = skillneedforjobs;
	}

	public Skillneedforjob addSkillneedforjob(Skillneedforjob skillneedforjob) {
		getSkillneedforjobs().add(skillneedforjob);
		skillneedforjob.setSkill(this);

		return skillneedforjob;
	}

	public Skillneedforjob removeSkillneedforjob(Skillneedforjob skillneedforjob) {
		getSkillneedforjobs().remove(skillneedforjob);
		skillneedforjob.setSkill(null);

		return skillneedforjob;
	}

}