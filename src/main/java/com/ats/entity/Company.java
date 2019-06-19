package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {
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
    @JsonIgnore
    private Users usersByUserId;
    @JsonIgnore
    private City cityByCityId;
    @JsonIgnore
    private List<Companyindustry> companyindustriesById;
    @JsonIgnore
    private List<Employercompany> employercompaniesById;
    @JsonIgnore
    private List<Job> jobsById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NameCompany", nullable = true, length = 50)
    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Basic
    @Column(name = "UserID", nullable = false, insertable = false , updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CityID", nullable = false , insertable = false , updatable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "Address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "TelephoneNumber", nullable = true, length = 10)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "LogoImg", nullable = true, length = -1)
    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "LastModify", nullable = true)
    public Timestamp getLastModify() {
        return lastModify;
    }

    public void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                userId == company.userId &&
                cityId == company.cityId &&
                Objects.equals(nameCompany, company.nameCompany) &&
                Objects.equals(address, company.address) &&
                Objects.equals(telephoneNumber, company.telephoneNumber) &&
                Objects.equals(email, company.email) &&
                Objects.equals(logoImg, company.logoImg) &&
                Objects.equals(description, company.description) &&
                Objects.equals(createdDate, company.createdDate) &&
                Objects.equals(lastModify, company.lastModify) &&
                Objects.equals(status, company.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCompany, userId, cityId, address, telephoneNumber, email, logoImg, description, createdDate, lastModify, status);
    }

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "CityID", referencedColumnName = "ID", nullable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public List<Companyindustry> getCompanyindustriesById() {
        return companyindustriesById;
    }

    public void setCompanyindustriesById(List<Companyindustry> companyindustriesById) {
        this.companyindustriesById = companyindustriesById;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public List<Employercompany> getEmployercompaniesById() {
        return employercompaniesById;
    }

    public void setEmployercompaniesById(List<Employercompany> employercompaniesById) {
        this.employercompaniesById = employercompaniesById;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public List<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(List<Job> jobsById) {
        this.jobsById = jobsById;
    }
}
