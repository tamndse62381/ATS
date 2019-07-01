package com.ats.dto;

import com.ats.entity.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CVDTO implements Serializable {
    private int id;
    private String title;
    private int userId;
    private String img;
    private String email;
    private String telephoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp dob;
    private int cityId;
    private String address;
    private int industryId;
    private String description;
    private Integer yearExperience;
    private Double salaryFrom;
    private Double salaryTo;
    private String status;
    private Timestamp createdDate;
    private Timestamp lastModify;
    private Integer isActive;
    private List<CertificationDTO> certificationsById;
    private List<EducationDTO> educationsById;
    private List<ProjectorproductworkedDTO> projectorproductworkedsById;
    private List<SocialactivitiesDTO> socialactivitiesById;
    private List<WorkexperienceDTO> workexperiencesById;

    public CVDTO(int id, String title, int userId, String img, String email, String telephoneNumber, String firstName, String lastName, String gender, Timestamp dob, int cityId, String address, int industryId, String description, Integer yearExperience, Double salaryFrom, Double salaryTo, String status, Timestamp createdDate, Timestamp lastModify, Integer isActive, List<CertificationDTO> certificationsById, List<EducationDTO> educationsById, List<ProjectorproductworkedDTO> projectorproductworkedsById, List<SocialactivitiesDTO> socialactivitiesById, List<WorkexperienceDTO> workexperiencesById) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.img = img;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.cityId = cityId;
        this.address = address;
        this.industryId = industryId;
        this.description = description;
        this.yearExperience = yearExperience;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModify = lastModify;
        this.isActive = isActive;
        this.certificationsById = certificationsById;
        this.educationsById = educationsById;
        this.projectorproductworkedsById = projectorproductworkedsById;
        this.socialactivitiesById = socialactivitiesById;
        this.workexperiencesById = workexperiencesById;
    }

    public CVDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModify() {
        return lastModify;
    }

    public void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<CertificationDTO> getCertificationsById() {
        return certificationsById;
    }

    public void setCertificationsById(List<CertificationDTO> certificationsById) {
        this.certificationsById = certificationsById;
    }

    public List<EducationDTO> getEducationsById() {
        return educationsById;
    }

    public void setEducationsById(List<EducationDTO> educationsById) {
        this.educationsById = educationsById;
    }

    public List<ProjectorproductworkedDTO> getProjectorproductworkedsById() {
        return projectorproductworkedsById;
    }

    public void setProjectorproductworkedsById(List<ProjectorproductworkedDTO> projectorproductworkedsById) {
        this.projectorproductworkedsById = projectorproductworkedsById;
    }

    public List<SocialactivitiesDTO> getSocialactivitiesById() {
        return socialactivitiesById;
    }

    public void setSocialactivitiesById(List<SocialactivitiesDTO> socialactivitiesById) {
        this.socialactivitiesById = socialactivitiesById;
    }

    public List<WorkexperienceDTO> getWorkexperiencesById() {
        return workexperiencesById;
    }

    public void setWorkexperiencesById(List<WorkexperienceDTO> workexperiencesById) {
        this.workexperiencesById = workexperiencesById;
    }
}
