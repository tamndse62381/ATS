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
@Table(name = "logemployer")
@Entity
public class Logemployer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "EmployerID")
  private Integer EmployerID;

  @Column(name = "LogType")
  private String LogType;

  @Column(name = "CreateDate")
  private Date CreateDate;

  @Column(name = "Note")
  private String Note;

  
}