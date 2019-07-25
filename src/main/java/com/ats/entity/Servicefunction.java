package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Servicefunction {
    private int id;
    private String functionName;
    private String status;
    private List<Servicepackage> servicepackagesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "functionName", nullable = false, length = 45)
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 45)
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
        Servicefunction that = (Servicefunction) o;
        return id == that.id &&
                Objects.equals(functionName, that.functionName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, functionName);
    }

    @OneToMany(mappedBy = "servicefunctionByFunctionId")
    @JsonBackReference
    public List<Servicepackage> getServicepackagesById() {
        return servicepackagesById;
    }

    public void setServicepackagesById(List<Servicepackage> servicepackagesById) {
        this.servicepackagesById = servicepackagesById;
    }
}
