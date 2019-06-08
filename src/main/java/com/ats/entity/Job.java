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
@Table(name = "job")
@Entity
public class Job implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "userid")
  private Integer userid;

  @Column(name = "companyid")
  private Integer companyid;

  @Column(name = "title")
  private String title;

  @Column(name = "cityid")
  private Integer cityid;

  @Column(name = "address")
  private String address;

  @Column(name = "joblevelid")
  private Integer joblevelid;

  @Column(name = "vacancyName")
  private String vacancyName;

  @Column(name = "workingType")
  private String workingType;

  @Column(name = "numbeOfRecruitment")
  private Integer numbeOfRecruitment;

  @Column(name = "salaryFrom")
  private Double salaryFrom;

  @Column(name = "salaryTo")
  private Double salaryTo;

  @Column(name = "yearExperience")
  private Integer yearExperience;

  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name = "endDateForApply")
  private Date endDateForApply;

  @Column(name = "jobDescription")
  private String jobDescription;

  @Column(name = "additionalRequest")
  private String additionalRequest;

  @Column(name = "candidateBenefits")
  private String candidateBenefits;

  @Column(name = "status")
  private String status;

  
}