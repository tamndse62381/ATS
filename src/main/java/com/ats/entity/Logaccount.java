package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the logaccount database table.
 * 
 */
@Entity
@NamedQuery(name="Logaccount.findAll", query="SELECT l FROM Logaccount l")
public class Logaccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String logTypeID;

	private String note;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;

	public Logaccount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLogTypeID() {
		return this.logTypeID;
	}

	public void setLogTypeID(String logTypeID) {
		this.logTypeID = logTypeID;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Cv getCv() {
		return this.cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

}