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

@Table(name = "company")
@Data
@Entity
public class Company implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "NameCompany")
  private String nameCompany;

  @Column(name = "UserID", nullable = false)
  private Integer userID;

  @Column(name = "CityID", nullable = false)
  private Integer cityID;

  @Column(name = "Address")
  private String address;

  @Column(name = "TelephoneNumber")
  private String telephoneNumber;

  @Column(name = "Email")
  private String email;

  @Column(name = "LogoImg")
  private String logoImg;

  @Column(name = "Description")
  private String description;

  @Column(name = "CreatedDate")
  private Date createdDate;

  @Column(name = "LastModify")
  private Date lastModify;

  @Column(name = "Status")
  private String status;
}
