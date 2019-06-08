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
@Table(name = "users")
public class Users implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "email")
  private String email;

  @Column(name = "fullName")
  private String fullName;

  @Column(name = "password")
  private String password;

  @Column(name = "accessToken")
  private String accessToken;

  @Column(name = "roleid")
  private Integer roleid;

  @Column(name = "telephoneNumber")
  private String telephoneNumber;

  @Column(name = "gender")
  private String gender;

  @Column(name = "jobLevelID")
  private Integer jobLevelID;

  @Column(name = "vacancyName")
  private String vacancyName;

  @Column(name = "cityid")
  private Integer cityid;

  @Column(name = "address")
  private String address;

  @Column(name = "description")
  private String description;

  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name = "LastLogin")
  private Date LastLogin;

  @Column(name = "LastModify")
  private Date LastModify;

  @Column(name = "Status")
  private String Status;

  
}