package com.ats.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Apply {
    private int id;
    private int jobSeekerId;
    private int jobId;
    private Timestamp dayApply;
    private String status;
    private Cv cvByJobSeekerId;
    private Job jobByJobId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JobSeekerID", nullable = false , insertable = false , updatable = false)
    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
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
    @Column(name = "DayApply", nullable = true)
    public Timestamp getDayApply() {
        return dayApply;
    }

    public void setDayApply(Timestamp dayApply) {
        this.dayApply = dayApply;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return id == apply.id &&
                jobSeekerId == apply.jobSeekerId &&
                jobId == apply.jobId &&
                Objects.equals(dayApply, apply.dayApply) &&
                Objects.equals(status, apply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobSeekerId, jobId, dayApply, status);
    }

    @ManyToOne
    @JoinColumn(name = "JobSeekerID", referencedColumnName = "ID", nullable = false)
    public Cv getCvByJobSeekerId() {
        return cvByJobSeekerId;
    }

    public void setCvByJobSeekerId(Cv cvByJobSeekerId) {
        this.cvByJobSeekerId = cvByJobSeekerId;
    }

    @ManyToOne
    @JoinColumn(name = "JobID", referencedColumnName = "ID", nullable = false)
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
    }
}
