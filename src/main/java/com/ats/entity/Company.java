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
@Table(name = "company")
@Data
public class Company implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "nameCompany")
  private String nameCompany;

  @Column(name = "userid")
  private Integer userid;

  @Column(name = "cityid")
  private Integer cityid;

  @Column(name = "address")
  private String address;

  @Column(name = "telephoneNumber")
  private String telephoneNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "logoImg")
  private String logoImg;

  @Column(name = "description")
  private String description;

  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name = "lastModify")
  private Date lastModify;

  @Column(name = "status")
  private String status;

  
}