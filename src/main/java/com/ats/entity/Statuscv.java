package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the statuscv database table.
 * 
 */
@Entity
@NamedQuery(name="Statuscv.findAll", query="SELECT s FROM Statuscv s")
public class Statuscv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Cv
	@OneToMany(mappedBy="statuscv")
	private List<Cv> cvs;

	public Statuscv() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cv> getCvs() {
		return this.cvs;
	}

	public void setCvs(List<Cv> cvs) {
		this.cvs = cvs;
	}

//	public Cv addCv(Cv cv) {
//		getCvs().add(cv);
//		cv.setStatuscv(this);
//
//		return cv;
//	}
//
//	public Cv removeCv(Cv cv) {
//		getCvs().remove(cv);
//		cv.setStatuscv(null);
//
//		return cv;
//	}

}