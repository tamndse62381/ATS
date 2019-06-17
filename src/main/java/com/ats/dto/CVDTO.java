package com.ats.dto;

import com.ats.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CVDTO implements Serializable {
    private Integer id;
    private String title;
    private Integer userid;
    private String img;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private Integer cityid;
    private String address;
    private Integer industryid;
    private String description;
    private Integer yearExperience;
    private Double salaryFrom;
    private Double salaryTo;
    private String status;
    private Date createdDate;
    private Date lastModify;
    private Integer isActive;
    private List<Certification> certifications;
    private List<Education> educations;
    private List<Socialactivities> socialactivities;
    private List<Workexperience> workexperiences;
    private List<Projectorproductworked> projectorproductworkeds;
    private List<Skillincv> skillincvs;

    public CVDTO() {
    }

    public CVDTO(Integer id, String title, Integer userid, String img, String email, String firstName, String lastName, String gender, Date dob, Integer cityid, String address, Integer industryid, String description, Integer yearExperience, Double salaryFrom, Double salaryTo, String status, Date createdDate, Date lastModify, Integer isActive, List<Certification> certifications, List<Education> educations, List<Socialactivities> socialactivities, List<Workexperience> workexperiences, List<Projectorproductworked> projectorproductworkeds, List<Skillincv> skillincvs) {
        this.id = id;
        this.title = title;
        this.userid = userid;
        this.img = img;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.cityid = cityid;
        this.address = address;
        this.industryid = industryid;
        this.description = description;
        this.yearExperience = yearExperience;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModify = lastModify;
        this.isActive = isActive;
        this.certifications = certifications;
        this.educations = educations;
        this.socialactivities = socialactivities;
        this.workexperiences = workexperiences;
        this.projectorproductworkeds = projectorproductworkeds;
        this.skillincvs = skillincvs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIndustryid() {
        return industryid;
    }

    public void setIndustryid(Integer industryid) {
        this.industryid = industryid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(Integer yearExperience) {
        this.yearExperience = yearExperience;
    }

    public Double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Double salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Socialactivities> getSocialactivities() {
        return socialactivities;
    }

    public void setSocialactivities(List<Socialactivities> socialactivities) {
        this.socialactivities = socialactivities;
    }

    public List<Workexperience> getWorkexperiences() {
        return workexperiences;
    }

    public void setWorkexperiences(List<Workexperience> workexperiences) {
        this.workexperiences = workexperiences;
    }

    public List<Projectorproductworked> getProjectorproductworkeds() {
        return projectorproductworkeds;
    }

    public void setProjectorproductworkeds(List<Projectorproductworked> projectorproductworkeds) {
        this.projectorproductworkeds = projectorproductworkeds;
    }

    public List<Skillincv> getSkillincvs() {
        return skillincvs;
    }

    public void setSkillincvs(List<Skillincv> skillincvs) {
        this.skillincvs = skillincvs;
    }
}
