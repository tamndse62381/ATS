package com.ats.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@Entity
@Table(name = "job")
public class Job implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "ID", insertable = false, nullable = false)
  @JsonProperty("ID")
  private Integer ID;

  @JsonProperty("EmployerID")
  private Integer EmployerID;

  @JsonProperty("CompanyID")
  private Integer CompanyID;

  @JsonProperty("Title")
  private String Title;

  @JsonProperty("CityID")
  private Integer CityID;

  @JsonProperty("Address")
  private String Address;

  @JsonProperty("JobLevelID")
  private Integer JobLevelID;

  @JsonProperty("VacancyName")
  private String VacancyName;

  @JsonProperty("SalaryFrom")
  private Double SalaryFrom;

  @JsonProperty("SalaryTo")
  private Double SalaryTo;

  @JsonProperty("YearExperience")
  private Integer YearExperience;

  @JsonProperty("CreatedDate")
  private Date CreatedDate;

  @JsonProperty("EndDateForApply")
  private Date EndDateForApply;

  @JsonProperty("JobDescription")
  private String JobDescription;

  @JsonProperty("AdditionalRequest")
  private String AdditionalRequest;

  @JsonProperty("CandidateBenefits")
  private String CandidateBenefits;

  @JsonProperty("Status")
  private String Status;

  
}