package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skilltype database table.
 * 
 */
@Entity
@NamedQuery(name="Skilltype.findAll", query="SELECT s FROM Skilltype s")
public class Skilltype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String typeName;

	//bi-directional many-to-one association to Skillmaster
	@OneToMany(mappedBy="skilltype")
	private List<Skillmaster> skillmasters;

	public Skilltype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Skillmaster> getSkillmasters() {
		return this.skillmasters;
	}

	public void setSkillmasters(List<Skillmaster> skillmasters) {
		this.skillmasters = skillmasters;
	}

	public Skillmaster addSkillmaster(Skillmaster skillmaster) {
		getSkillmasters().add(skillmaster);
		skillmaster.setSkilltype(this);

		return skillmaster;
	}

	public Skillmaster removeSkillmaster(Skillmaster skillmaster) {
		getSkillmasters().remove(skillmaster);
		skillmaster.setSkilltype(null);

		return skillmaster;
	}

}