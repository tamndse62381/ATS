package com.ats.dto;

import lombok.Data;

@Data
public class JobDTO {
    private int id;
    private String title;
    private int companyid;
    private String companyName;
    private int cityid;
    private String cityName;
    private Double salaryTo;
    private Double salaryFrom;

}
