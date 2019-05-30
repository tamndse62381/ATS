package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the school database table.
 * 
 */
@Entity
@NamedQuery(name="School.findAll", query="SELECT s FROM School s")
public class School implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String schoolName;

	//bi-directional many-to-one association to Education
	@OneToMany(mappedBy="school")
	private List<Education> educations;

	//bi-directional many-to-one association to Schooltype
	@ManyToOne
	@JoinColumn(name="SchoolTypeID")
	private Schooltype schooltype;

	public School() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public Education addEducation(Education education) {
		getEducations().add(education);
		education.setSchool(this);

		return education;
	}

	public Education removeEducation(Education education) {
		getEducations().remove(education);
		education.setSchool(null);

		return education;
	}

	public Schooltype getSchooltype() {
		return this.schooltype;
	}

	public void setSchooltype(Schooltype schooltype) {
		this.schooltype = schooltype;
	}

}