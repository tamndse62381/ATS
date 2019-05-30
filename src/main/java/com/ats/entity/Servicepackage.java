package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the servicepackage database table.
 * 
 */
@Entity
@NamedQuery(name="Servicepackage.findAll", query="SELECT s FROM Servicepackage s")
public class Servicepackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Time duration;

	private String name;

	private String status;

	//bi-directional many-to-one association to Receipt
	@OneToMany(mappedBy="servicepackage")
	private List<Receipt> receipts;

	public Servicepackage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getDuration() {
		return this.duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Receipt> getReceipts() {
		return this.receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	public Receipt addReceipt(Receipt receipt) {
		getReceipts().add(receipt);
		receipt.setServicepackage(this);

		return receipt;
	}

	public Receipt removeReceipt(Receipt receipt) {
		getReceipts().remove(receipt);
		receipt.setServicepackage(null);

		return receipt;
	}

}