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
@Table(name = "cv")
@Data
public class Cv implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "userid")
  private Integer userid;

  @Column(name = "img")
  private String img;

  @Column(name = "email")
  private String email;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "gender")
  private String gender;

  @Column(name = "dob")
  private Date dob;

  @Column(name = "cityid")
  private Integer cityid;

  @Column(name = "address")
  private String address;

  @Column(name = "industryid")
  private Integer industryid;

  @Column(name = "description")
  private String description;

  @Column(name = "yearExperience")
  private Integer yearExperience;

  @Column(name = "salaryFrom")
  private Double salaryFrom;

  @Column(name = "salaryTo")
  private Double salaryTo;

  @Column(name = "status")
  private String status;

  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name = "lastModify")
  private Date lastModify;

  @Column(name = "isActive")
  private Integer isActive;

  
}