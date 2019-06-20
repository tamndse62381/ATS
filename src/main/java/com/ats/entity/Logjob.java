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

@Table(name = "logjob")
@Data
@Entity
public class Logjob implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "JobID", nullable = false)
  private Integer JobID;

  @Column(name = "CuratorID", nullable = false)
  private Integer CuratorID;

  @Column(name = "LogType", nullable = false)
  private Integer LogType;

  @Column(name = "CreatedDate")
  private Date CreatedDate;

  
}