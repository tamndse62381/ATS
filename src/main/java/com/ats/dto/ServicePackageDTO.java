package com.ats.dto;

import lombok.Data;

import java.util.Date;
import java.sql.Time;

@Data
public class ServicePackageDTO {
    private int id;
    private String name;
    private String status;
    private String duration;
    private String description;
    private Date createdDate;
    private Double price;
}
