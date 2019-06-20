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
@Table(name = "jobseekerlikejob")
public class Jobseekerlikejob implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "UserID", nullable = false)
  private Integer userid;

  @Column(name = "JobID", nullable = false)
  private Integer jobid;

  @Column(name = "CreatedDate")
  private Date createdDate;

  @Column(name = "LastModifyDate")
  private Date lastModifyDate;

  @Column(name = "isActive")
  private Integer isActive;

  
}