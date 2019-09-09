package com.ats.dto;

import com.ats.entity.Job;

import java.io.Serializable;

public class VectorDTO implements Serializable {
    private JobDTO4 job;
    private int cvId;
    private double vectorJobAndCv;

    public VectorDTO() {
    }

    public VectorDTO(JobDTO4 job, int cvId, double vectorJobAndCv) {
        this.job = job;
        this.cvId = cvId;
        this.vectorJobAndCv = vectorJobAndCv;
    }

    public JobDTO4 getJob() {
        return job;
    }

    public void setJob(JobDTO4 job) {
        this.job = job;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public double getVectorJobAndCv() {
        return vectorJobAndCv;
    }

    public void setVectorJobAndCv(double vectorJobAndCv) {
        this.vectorJobAndCv = vectorJobAndCv;
    }
}
