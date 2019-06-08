package com.ats.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employer")
@Data
public class Employer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "AccountID")
  private Integer AccountID;

  @Column(name = "Email")
  private String Email;

  @Column(name = "FullName")
  private String FullName;

  @Column(name = "TelephoneNumber")
  private String TelephoneNumber;

  @Column(name = "Gender")
  private String Gender;

  @Column(name = "JobLevelID")
  private Integer JobLevelID;

  @Column(name = "VacancyName")
  private String VacancyName;

  @Column(name = "CityID")
  private Integer CityID;

  @Column(name = "Address")
  private String Address;

  @Column(name = "Description")
  private String Description;

  @Column(name = "Status")
  private String Status;

  
}