package com.ats.dto;

import com.ats.entity.Job;

import java.io.Serializable;

public class VectorDTO implements Serializable {
    private JobDTO4 job;
    private int cvId;
    private double vectorJobAndCv;
    private double lenghtVectorCV;
    private double average;

    public VectorDTO(JobDTO4 job, int cvId, double vectorJobAndCv, double lenghtVectorCV, double average) {
        this.job = job;
        this.cvId = cvId;
        this.vectorJobAndCv = vectorJobAndCv;
        this.lenghtVectorCV = lenghtVectorCV;
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public VectorDTO() {
    }

    public VectorDTO(JobDTO4 job, int cvId, double vectorJobAndCv) {
        this.job = job;
        this.cvId = cvId;
        this.vectorJobAndCv = vectorJobAndCv;
    }

    public VectorDTO(JobDTO4 job, int cvId, double vectorJobAndCv, double lenghtVectorCV) {
        this.job = job;
        this.cvId = cvId;
        this.vectorJobAndCv = vectorJobAndCv;
        this.lenghtVectorCV = lenghtVectorCV;
    }

    public double getLenghtVectorCV() {
        return lenghtVectorCV;
    }

    public void setLenghtVectorCV(double lenghtVectorCV) {
        this.lenghtVectorCV = lenghtVectorCV;
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
