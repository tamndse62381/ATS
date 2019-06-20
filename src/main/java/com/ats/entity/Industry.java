package com.ats.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Industry {
    private int id;
    private String name;
    private List<Companyindustry> companyindustriesById;
    private List<Cv> cvsById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Industry industry = (Industry) o;
        return id == industry.id &&
                Objects.equals(name, industry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "industryByIndustryId")
    public List<Companyindustry> getCompanyindustriesById() {
        return companyindustriesById;
    }

    public void setCompanyindustriesById(List<Companyindustry> companyindustriesById) {
        this.companyindustriesById = companyindustriesById;
    }

    @OneToMany(mappedBy = "industryByIndustryId")
    public List<Cv> getCvsById() {
        return cvsById;
    }

    public void setCvsById(List<Cv> cvsById) {
        this.cvsById = cvsById;
    }
}
