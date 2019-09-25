package com.ats.dto;

import com.ats.enummerator.WorkingType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobDTO4 {
     Integer id;
     Integer userId;
     Integer companyId;
     Integer industryId;
     String title;
     Integer cityId;
     String address;
     Integer joblevelId;
     WorkingType workingtype;
     Integer numbeOfRecruitment;
     Double salaryFrom;
     Double salaryTo;
     Integer yearExperience;
     Date createdDate;
     Date lastmodifyDate;
     Date endDateForApply;
     String jobDescription;
     String additionalRequest;
     String candidateBenefits;
     String status;
     List<SkillDTO2> listSkill;

    public JobDTO4() {
    }

    public JobDTO4(Integer id, Integer cityId, Double salaryFrom, Double salaryTo,
                   List<SkillDTO2> listSkill) {
        this.id = id;
        this.userId = 0;
        this.companyId = 1;
        this.industryId = 0;
        this.title = "";
        this.cityId = cityId;
        this.address = "";
        this.joblevelId = 0;
        this.workingtype = WorkingType.FULLTIME;
        this.numbeOfRecruitment = 0;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.yearExperience = 0;
        this.createdDate = new Date();
        this.lastmodifyDate = new Date();
        this.endDateForApply = new Date();
        this.jobDescription = "";
        this.additionalRequest = "";
        this.candidateBenefits = "";
        this.status = "";
        this.listSkill = listSkill;
    }


}
