package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the socialactivities database table.
 * 
 */
@Entity
@Table(name="socialactivities")
@NamedQuery(name="Socialactivity.findAll", query="SELECT s FROM Socialactivity s")
public class Socialactivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	public Socialactivity() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

}