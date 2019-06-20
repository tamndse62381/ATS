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

@Table(name = "countcv")
@Entity
@Data
public class Countcv implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "UserID", nullable = false)
  private Integer UserID;

  @Column(name = "CVID", nullable = false)
  private Integer CVID;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  
}