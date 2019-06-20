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
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "JobSeekerID", nullable = false)
  private Integer jobseekerid;

  @Column(name = "JobID", nullable = false)
  private Integer jobid;

  @Column(name = "DayApply")
  private Date dayApply;

  @Column(name = "Status")
  private String status;


}
