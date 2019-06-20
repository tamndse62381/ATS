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
  private String title;

  @Column(name = "UserID", nullable = false)
  private Integer userID;

  @Column(name = "Img")
  private String img;

  @Column(name = "Email")
  private String email;

  @Column(name = "TelephoneNumber")
  private String telephoneNumber;

  @Column(name = "FirstName")
  private String firstName;

  @Column(name = "LastName")
  private String lastName;

  @Column(name = "Gender")
  private String gender;

  @Column(name = "DOB")
  private Date dob;

  @Column(name = "CityID", nullable = false)
  private Integer cityID;

  @Column(name = "Address")
  private String address;

  @Column(name = "IndustryID", nullable = false)
  private Integer industryID;

  @Column(name = "Description")
  private String description;

  @Column(name = "YearExperience")
  private Integer yearExperience;

  @Column(name = "SalaryFrom")
  private Double salaryFrom;

  @Column(name = "SalaryTo")
  private Double salaryTo;

  @Column(name = "Status")
  private String status;

  @Column(name = "CreatedDate")
  private Date createdDate;

  @Column(name = "LastModify")
  private Date lastModify;

  @Column(name = "isActive")
  private Integer isActive;
}
