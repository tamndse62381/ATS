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

@Entity
@Data
@Table(name = "education")
public class Education implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "cvid")
  private Integer cvid;

  @Column(name = "schooltype")
  private String schooltype;

  @Column(name = "major")
  private String major;

  @Column(name = "starttime")
  private Date starttime;

  @Column(name = "endtime")
  private Date endtime;

  @Column(name = "description")
  private String description;
}