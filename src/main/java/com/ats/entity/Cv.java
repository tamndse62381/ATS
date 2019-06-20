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
@Table(name = "cv")
public class Cv implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "Title")
  private String Title;

  @Column(name = "UserID", nullable = false)
  private Integer UserID;

  @Column(name = "Img")
  private String Img;

  @Column(name = "Email")
  private String Email;

  @Column(name = "TelephoneNumber")
  private String TelephoneNumber;

  @Column(name = "FirstName")
  private String FirstName;

  @Column(name = "LastName")
  private String LastName;

  @Column(name = "Gender")
  private String Gender;

  @Column(name = "DOB")
  private Date DOB;

  @Column(name = "CityID", nullable = false)
  private Integer CityID;

  @Column(name = "Address")
  private String Address;

  @Column(name = "IndustryID", nullable = false)
  private Integer IndustryID;

  @Column(name = "Description")
  private String Description;

  @Column(name = "YearExperience")
  private Integer YearExperience;

  @Column(name = "SalaryFrom")
  private Double SalaryFrom;

  @Column(name = "SalaryTo")
  private Double SalaryTo;

  @Column(name = "Status")
  private String Status;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  @Column(name = "LastModify")
  private Date LastModify;

  @Column(name = "isActive")
  private Integer isActive;

  
}