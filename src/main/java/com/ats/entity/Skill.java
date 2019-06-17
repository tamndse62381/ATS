package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "skill")
@Data
public class Skill implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "skillmasterid")
  private Integer skillmasterid;

  @Column(name = "skilllevelid")
  private Integer skilllevelid;
}
