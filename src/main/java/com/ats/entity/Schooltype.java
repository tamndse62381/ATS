package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the schooltype database table.
 * 
 */
@Entity
@NamedQuery(name="Schooltype.findAll", query="SELECT s FROM Schooltype s")
public class Schooltype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String type;

	//bi-directional many-to-one association to School
	@OneToMany(mappedBy="schooltype")
	private List<School> schools;

	public Schooltype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<School> getSchools() {
		return this.schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public School addSchool(School school) {
		getSchools().add(school);
		school.setSchooltype(this);

		return school;
	}

	public School removeSchool(School school) {
		getSchools().remove(school);
		school.setSchooltype(null);

		return school;
	}

}