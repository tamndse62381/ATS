package com.ats.dto;

import com.ats.entity.Job;
import lombok.Data;

@Data
public class SuggestDTO {
    double div;
    Job job;

    public void setDiv(double div) {
        this.div = div;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public double getDiv() {
        return div;
    }

    public Job getJob() {
        return job;
    }
}
