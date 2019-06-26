package com.ats.dto;

import com.ats.entity.Skill;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobDTO2 {
    private Integer id;
    private Integer userid;
    private Integer companyid;
    private String title;
    private Integer cityid;
    private String address;
    private Integer joblevelid;
    private String vacancyname;
    private String workingtype;
    private Integer numberofrecruitment;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer yearExperience;
    private Date createdDate;
    private Date endDateForApply;
    private String jobDescription;
    private String additionalRequest;
    private String candidateBenefits;
    private String status;

}
