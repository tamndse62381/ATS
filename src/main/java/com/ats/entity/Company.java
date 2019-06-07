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
@Table(name = "company")
public class Company implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "NameCompany")
  private String NameCompany;

  @Column(name = "EmployerID")
  private Integer EmployerID;

  @Column(name = "CityID")
  private Integer CityID;

  @Column(name = "Address")
  private String Address;

  @Column(name = "TelephoneNumber")
  private String TelephoneNumber;

  @Column(name = "Email")
  private String Email;

  @Column(name = "LogoImg")
  private String LogoImg;

  @Column(name = "Description")
  private String Description;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  @Column(name = "LastModify")
  private Date LastModify;

  @Column(name = "Status")
  private String Status;

  
}