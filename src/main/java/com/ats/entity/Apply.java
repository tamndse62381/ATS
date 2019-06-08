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
@Table(name = "apply")
public class Apply implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "jobseekerid")
  private Integer jobseekerid;

  @Column(name = "jobid")
  private Integer jobid;

  @Column(name = "dayApply")
  private Date dayApply;

  @Column(name = "status")
  private String status;

  
}