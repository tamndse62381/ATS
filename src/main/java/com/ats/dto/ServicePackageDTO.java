package com.ats.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class ServicePackageDTO {
    private int id;
    private String name;
    private String status;
    private Time duration;
}
