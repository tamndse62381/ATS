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
  private Integer id;

  @Column(name = "Email")
  private String email;

  @Column(name = "FullName")
  private String fullName;

  @Column(name = "Password")
  private String password;

  @Column(name = "AccessToken")
  private String accessToken;

  @Column(name = "RoleId", nullable = false)
  private Integer roleid;

  @Column(name = "TelephoneNumber")
  private String telephoneNumber;

  @Column(name = "Gender")
  private String gender;

  @Column(name = "JobLevelID")
  private Integer joblevelid;

  @Column(name = "VacancyName")
  private String vacancyName;

  @Column(name = "CityID")
  private Integer cityid;

  @Column(name = "Address")
  private String address;

  @Column(name = "Description")
  private String description;

  @Column(name = "CreatedDate")
  private Date createdDate;

  @Column(name = "LastLogin")
  private Date lastLogin;

  @Column(name = "LastModify")
  private Date lastModify;

  @Column(name = "Status")
  private String status;

  
}