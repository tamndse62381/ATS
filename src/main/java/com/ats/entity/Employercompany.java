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

@Table(name = "employercompany")
@Entity
@Data
public class Employercompany implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "UserID", nullable = false)
  private Integer userid;

  @Column(name = "CompanyID", nullable = false)
  private Integer companyid;

  @Column(name = "CreatedDate")
  private Date createdDate;


}
