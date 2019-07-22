package com.ats.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Functionpackage {
    private int id;
    private int servicePackageId;
    private int serviceFunctionId;
    private Servicepackage servicepackageByServicePackageId;
    private Servicefunction servicefunctionByServiceFunctionId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "servicePackageId", nullable = false, insertable = false, updatable = false)
    public int getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(int servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    @Basic
    @Column(name = "serviceFunctionId", nullable = false, insertable = false, updatable = false)
    public int getServiceFunctionId() {
        return serviceFunctionId;
    }

    public void setServiceFunctionId(int serviceFunctionId) {
        this.serviceFunctionId = serviceFunctionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Functionpackage that = (Functionpackage) o;
        return id == that.id &&
                servicePackageId == that.servicePackageId &&
                serviceFunctionId == that.serviceFunctionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicePackageId, serviceFunctionId);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "servicePackageId", referencedColumnName = "ID", nullable = false)
    public Servicepackage getServicepackageByServicePackageId() {
        return servicepackageByServicePackageId;
    }

    public void setServicepackageByServicePackageId(Servicepackage servicepackageByServicePackageId) {
        this.servicepackageByServicePackageId = servicepackageByServicePackageId;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "serviceFunctionId", referencedColumnName = "id", nullable = false)
    public Servicefunction getServicefunctionByServiceFunctionId() {
        return servicefunctionByServiceFunctionId;
    }

    public void setServicefunctionByServiceFunctionId(Servicefunction servicefunctionByServiceFunctionId) {
        this.servicefunctionByServiceFunctionId = servicefunctionByServiceFunctionId;
    }
}
