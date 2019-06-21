package com.ats.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Integer id;
    private String title;
    private String companyid;
    private String companyName;
    private Integer cityid;
    private String cityName;
    private Double salaryTo;
    private Double salaryFrom;

}
