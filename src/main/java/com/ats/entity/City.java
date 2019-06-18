package com.ats.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class City {
    private int id;
    private String fullName;
    private List<Company> companiesById;
    private List<Cv> cvsById;
    private List<Job> jobsById;
    private List<Users> usersById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FullName", nullable = true, length = 50)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(fullName, city.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @OneToMany(mappedBy = "cityByCityId")
    public List<Company> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(List<Company> companiesById) {
        this.companiesById = companiesById;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public List<Cv> getCvsById() {
        return cvsById;
    }

    public void setCvsById(List<Cv> cvsById) {
        this.cvsById = cvsById;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public List<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(List<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public List<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(List<Users> usersById) {
        this.usersById = usersById;
    }
}
