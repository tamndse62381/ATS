package com.ats.dto;

public class EmployerDTO {
    int id;
    int accountId;
    String email;
    String fullname;
    String telephoneNumber;
    String gender;
    int joblevelId;
    String vacancyName;
    int cityId;
    String address;
    String description;
    String status;

    public EmployerDTO() {
        super();
    }

    public EmployerDTO(int id, int accountId, String email, String fullname, String telephoneNumber, String gender, int joblevelId,
                       String vacancyName, int cityId, String address, String description, String status) {
        this.id = id;
        this.accountId = accountId;
        this.email = email;
        this.fullname = fullname;
        this.telephoneNumber = telephoneNumber;
        this.gender = gender;
        this.joblevelId = joblevelId;
        this.vacancyName = vacancyName;
        this.cityId = cityId;
        this.address = address;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public int getJoblevelId() {
        return joblevelId;
    }

    public void setJoblevelId(int joblevelId) {
        this.joblevelId = joblevelId;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
