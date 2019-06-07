package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * The persistent class for the education database table.
 * 
 */
@Entity
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

//	@Temporal(TemporalType.DATE)
//	private LocalDateTime endtime;

	private String major;

	private String schoolType;

//	@Temporal(TemporalType.DATE)
//	private LocalDateTime startTime;

	//bi-directional many-to-one association to Cv
	@ManyToOne
	@JoinColumn(name="CVID")
	private Cv cv;
}