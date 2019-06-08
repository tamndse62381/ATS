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

@Table(name = "logusers")
@Entity
@Data
public class Logusers implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "cvid")
  private Integer cvid;

  @Column(name = "logtypeid")
  private String logtypeid;

  @Column(name = "createDate")
  private Date createDate;

  @Column(name = "note")
  private String note;

  
}