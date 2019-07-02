package com.ats.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class CompanyDTO {
    private int id;
    private String nameCompany;
    private int userId;
    private int cityId;
    private String address;
    private String telephoneNumber;
    private String email;
    private String logoImg;
    private String description;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private String status;
}
