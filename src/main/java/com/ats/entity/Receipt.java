package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the receipts database table.
 * 
 */
@Entity
@Table(name="receipts")
@NamedQuery(name="Receipt.findAll", query="SELECT r FROM Receipt r")
public class Receipt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paiedDay;

	//bi-directional many-to-one association to Employer
	@ManyToOne
	@JoinColumn(name="HRID")
	private Employer employer;

	//bi-directional many-to-one association to Servicepackage
	@ManyToOne
	@JoinColumn(name="ServicePackageID")
	private Servicepackage servicepackage;

	public Receipt() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaiedDay() {
		return this.paiedDay;
	}

	public void setPaiedDay(Date paiedDay) {
		this.paiedDay = paiedDay;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Servicepackage getServicepackage() {
		return this.servicepackage;
	}

	public void setServicepackage(Servicepackage servicepackage) {
		this.servicepackage = servicepackage;
	}

}