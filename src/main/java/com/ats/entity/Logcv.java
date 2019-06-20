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
@Table(name = "logcv")
@Data
public class Logcv implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "CVID", nullable = false)
  private Integer CVID;

  @Column(name = "CuratorID", nullable = false)
  private Integer CuratorID;

  @Column(name = "LogType", nullable = false)
  private Integer LogType;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  
}