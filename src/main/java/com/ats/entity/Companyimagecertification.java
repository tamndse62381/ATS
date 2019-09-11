package com.ats.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Companyimagecertification {
    private int id;
    private Integer companyid;
    private String source;
    private Company companyByCompanyid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "companyid", nullable = true, insertable = false , updatable = false)
    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    @Basic
    @Column(name = "source", nullable = true, length = -1)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companyimagecertification that = (Companyimagecertification) o;
        return id == that.id &&
                Objects.equals(companyid, that.companyid) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyid, source);
    }

    @ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "ID")
    public Company getCompanyByCompanyid() {
        return companyByCompanyid;
    }

    public void setCompanyByCompanyid(Company companyByCompanyid) {
        this.companyByCompanyid = companyByCompanyid;
    }
}
