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
@Table(name = "account")
@Data
public class Account implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "Email", nullable = false)
  private String Email;

  @Column(name = "Password")
  private String Password;

  @Column(name = "FullName")
  private String FullName;

  @Column(name = "RoleId")
  private Integer RoleId;

  @Column(name = "Status")
  private String Status;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  @Column(name = "LastLogin")
  private Date LastLogin;

  @Column(name = "LastModify")
  private Date LastModify;

  @Column(name = "AccessToken")
  private String AccessToken;

  
}