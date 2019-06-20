package com.ats.dto;

import lombok.Data;

import java.util.Date;


@Data
public class UsersDTO{
    int id;
    String email;
    String password;
    String newPassword;
    String fullname;
    String status;
    Date createdDate;
    Date lastLogin;
    Date lastModify;
    int roleId;
    String accessToken;
    String telephoneNumber;
    String gender;
    int jobLevelID;
    String vacancyName;
    int cityid;
    String address;
    String description;


    public UsersDTO() {
        super();
    }


    public UsersDTO(int id, String fullname, String email, int roleId, String accessToken) {
        super();
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.roleId = roleId;
        this.accessToken = accessToken;
    }


    public UsersDTO(int id, String email, String password, String newPassword, String fullname,
                    String status, Date createdDate, Date lastLogin, Date lastModify,
                    int roleId, String accessToken, String telephoneNumber, String gender,
                    int jobLevelID, String vacancyName, int cityid, String address, String description) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
        this.fullname = fullname;
        this.status = status;
        this.createdDate = createdDate;
        this.lastLogin = lastLogin;
        this.lastModify = lastModify;
        this.roleId = roleId;
        this.accessToken = accessToken;
        this.telephoneNumber = telephoneNumber;
        this.gender = gender;
        this.jobLevelID = jobLevelID;
        this.vacancyName = vacancyName;
        this.cityid = cityid;
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }



    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getJobLevelID() {
        return jobLevelID;
    }

    public void setJobLevelID(int jobLevelID) {
        this.jobLevelID = jobLevelID;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}