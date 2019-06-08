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

@Data
@Entity
@Table(name = "projectorproductworked")
public class Projectorproductworked implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "cvid")
  private Integer cvid;

  @Column(name = "vacancyName")
  private String vacancyName;

  @Column(name = "startTime")
  private Date startTime;

  @Column(name = "endTime")
  private Date endTime;

  @Column(name = "skillUsed")
  private String skillUsed;

  @Column(name = "description")
  private String description;

  
}