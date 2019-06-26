package com.ats.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "job")
@Data
@Entity
public class Job implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "UserID", nullable = false)
  private Integer userid;

  @Column(name = "CompanyID", nullable = false)
  private Integer companyid;

  @Column(name = "Title")
  private String title;

  @Column(name = "CityID", nullable = false)
  private Integer cityid;

  @Column(name = "Address")
  private String address;

  @Column(name = "JobLevelID", nullable = false)
  private Integer joblevelid;

  @Column(name = "VacancyName")
  private String vacancyname;

  @Column(name = "WorkingType")
  private String workingtype;

  @Column(name = "NumbeOfRecruitment")
  private Integer numberofrecruitment;

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
