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
@Table(name = "job")
public class Job implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "EmployerID")
  private Integer employerID;

  @Column(name = "CompanyID")
  private Integer companyID;

  @Column(name = "Title")
  private String title;

  @Column(name = "CityID")
  private Integer cityID;

  @Column(name = "Address")
  private String address;

  @Column(name = "JobLevelID")
  private Integer jobLevelID;

  @Column(name = "VacancyName")
  private String vacancyName;

  @Column(name = "SalaryFrom")
  private Double salaryFrom;

  @Column(name = "SalaryTo")
  private Double salaryTo;

  @Column(name = "YearExperience")
  private Integer yearExperience;

  @Column(name = "CreatedDate")
  private Date createdDate;

  @Column(name = "EndDateForApply")
  private Date endDateForApply;

  @Column(name = "JobDescription")
  private String jobDescription;

  @Column(name = "AdditionalRequest")
  private String additionalRequest;

  @Column(name = "CandidateBenefits")
  private String candidateBenefits;

  @Column(name = "Status")
  private String status;

  
}