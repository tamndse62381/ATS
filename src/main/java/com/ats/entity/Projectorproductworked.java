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
  private Integer ID;

  @Column(name = "CVID", nullable = false)
  private Integer CVID;

  @Column(name = "ProjetName")
  private String ProjetName;

  @Column(name = "VacancyName")
  private String VacancyName;

  @Column(name = "StartTime")
  private Date StartTime;

  @Column(name = "EndTime")
  private Date EndTime;

  @Column(name = "SkillUsed")
  private String SkillUsed;

  @Column(name = "Description")
  private String Description;

  
}