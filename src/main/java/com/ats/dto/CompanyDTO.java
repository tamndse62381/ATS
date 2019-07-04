package com.ats.dto;

import com.ats.entity.Companyindustry;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

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
    private List<CompanyindustryDTO> companyindustriesById;
}
