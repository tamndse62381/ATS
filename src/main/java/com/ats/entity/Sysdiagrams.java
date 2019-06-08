package com.ats.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "sysdiagrams")
@Data
@Entity
public class Sysdiagrams implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "principal_id", nullable = false)
  private Integer principalId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "diagram_id", insertable = false, nullable = false)
  private Integer diagramId;

  @Column(name = "version")
  private Integer version;

  @Column(name = "definition")
  private String definition;

  
}