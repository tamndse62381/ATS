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

@Table(name = "projectorproductworked")
@Entity
@Data
public class Projectorproductworked implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "CVID", nullable = false)
  private Integer cvid;

  @Column(name = "ProjetName")
  private String projectName;

  @Column(name = "VacancyName")
  private String vacancyName;

  @Column(name = "StartTime")
  private Date startTime;

  @Column(name = "EndTime")
  private Date endTime;

  @Column(name = "SkillUsed")
  private String skillUsed;

  @Column(name = "Description")
  private String description;

  
}