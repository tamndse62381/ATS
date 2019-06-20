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
@Data
@Entity
public class Education implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "CVID", nullable = false)
  private Integer cvid;

  @Column(name = "SchoolType")
  private String schoolType;

  @Column(name = "SchoolName")
  private String schoolName;

  @Column(name = "Major")
  private String major;

  @Column(name = "StartTime")
  private Date startTime;

  @Column(name = "Endtime")
  private Date endtime;

  @Column(name = "Description")
  private String description;


}
