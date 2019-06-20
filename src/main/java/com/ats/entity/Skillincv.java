package com.ats.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "skillincv")
@Data
@Entity
public class Skillincv implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", insertable = false, nullable = false)
  private Integer ID;

  @Column(name = "CVID", nullable = false)
  private Integer CVID;

  @Column(name = "SkillID", nullable = false)
  private Integer SkillID;

  
}