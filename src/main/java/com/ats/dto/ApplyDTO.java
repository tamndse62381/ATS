package com.ats.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApplyDTO {
    private int id;
    private int jobSeekerId;
    private int jobId;
    private Timestamp dayApply;
    private String status;
}
