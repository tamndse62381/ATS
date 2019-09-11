package com.ats.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Suggest {
    private int id;
    private int cvid;
    private int jobid;
    private Cv cvByCvid;
    private Job jobByJobid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cvid", nullable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    @Basic
    @Column(name = "jobid", nullable = false)
    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggest suggest = (Suggest) o;
        return id == suggest.id &&
                cvid == suggest.cvid &&
                jobid == suggest.jobid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvid, jobid);
    }

    @ManyToOne
    @JoinColumn(name = "cvid", referencedColumnName = "ID", nullable = false, insertable = false , updatable = false)
    public Cv getCvByCvid() {
        return cvByCvid;
    }

    public void setCvByCvid(Cv cvByCvid) {
        this.cvByCvid = cvByCvid;
    }

    @ManyToOne
    @JoinColumn(name = "jobid", referencedColumnName = "ID", nullable = false, insertable = false , updatable = false)
    public Job getJobByJobid() {
        return jobByJobid;
    }

    public void setJobByJobid(Job jobByJobid) {
        this.jobByJobid = jobByJobid;
    }
}
