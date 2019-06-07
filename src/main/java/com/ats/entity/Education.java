package com.ats.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "education")
@Entity
@Data
public class Education implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "CVID")
  private Integer CVID;

  @Column(name = "SchoolType")
  private String SchoolType;

  @Column(name = "Major")
  private String Major;

  @Column(name = "StartTime")
  private Date StartTime;

  @Column(name = "Endtime")
  private Date Endtime;

  @Column(name = "Description")
  private String Description;

  
}