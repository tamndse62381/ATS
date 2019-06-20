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
@Table(name = "users")
public class Users implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "Email")
  private String Email;

  @Column(name = "FullName")
  private String FullName;

  @Column(name = "Password")
  private String Password;

  @Column(name = "AccessToken")
  private String AccessToken;

  @Column(name = "RoleId", nullable = false)
  private Integer RoleId;

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

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  @Column(name = "LastLogin")
  private Date LastLogin;

  @Column(name = "LastModify")
  private Date LastModify;

  @Column(name = "Status")
  private String Status;

  
}