package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Skillneedforjob {
    private int id;
    private int jobId;
    private int skillId;
    private Job jobByJobId;
    private Skill skillBySkillId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobID", nullable = false , insertable = false , updatable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "SkillID", nullable = false , insertable = false , updatable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skillneedforjob that = (Skillneedforjob) o;
        return id == that.id &&
                jobId == that.jobId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobId, skillId);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "JobID", referencedColumnName = "ID", nullable = false)
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "SkillID", referencedColumnName = "ID", nullable = false)
    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
    }
}
