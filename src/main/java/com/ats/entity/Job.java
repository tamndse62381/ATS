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
  private Integer ID;

  @Column(name = "EmployerID")
  private Integer EmployerID;

  @Column(name = "CompanyID")
  private Integer CompanyID;

  @Column(name = "Title")
  private String Title;

  @Column(name = "CityID")
  private Integer CityID;

  @Column(name = "Address")
  private String Address;

  @Column(name = "JobLevelID")
  private Integer JobLevelID;

  @Column(name = "VacancyName")
  private String VacancyName;

  @Column(name = "SalaryFrom")
  private Double SalaryFrom;

  @Column(name = "SalaryTo")
  private Double SalaryTo;

  @Column(name = "YearExperience")
  private Integer YearExperience;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  @Column(name = "EndDateForApply")
  private Date EndDateForApply;

  @Column(name = "JobDescription")
  private String JobDescription;

  @Column(name = "AdditionalRequest")
  private String AdditionalRequest;

  @Column(name = "CandidateBenefits")
  private String CandidateBenefits;

  @Column(name = "Status")
  private String Status;

  
}