package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skillmaster database table.
 * 
 */
@Entity
@NamedQuery(name="Skillmaster.findAll", query="SELECT s FROM Skillmaster s")
public class Skillmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String skillName;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="skillmaster")
	private List<Skill> skills;

	//bi-directional many-to-one association to Skilltype
	@ManyToOne
	@JoinColumn(name="SkillTypeID")
	private Skilltype skilltype;

	public Skillmaster() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setSkillmaster(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setSkillmaster(null);

		return skill;
	}

	public Skilltype getSkilltype() {
		return this.skilltype;
	}

	public void setSkilltype(Skilltype skilltype) {
		this.skilltype = skilltype;
	}

}