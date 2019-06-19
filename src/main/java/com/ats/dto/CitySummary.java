package com.ats.dto;

import com.ats.entity.Company;
import com.ats.entity.Cv;
import com.ats.entity.Job;
import com.ats.entity.Users;

import java.util.List;

public interface CitySummary {
     int getId();
     String getFullName();
     List<Company> getCompaniesById();
     List<Cv> getCvsById();
     List<Job> getJobsById();
     List<Users> getUsersById();
}
