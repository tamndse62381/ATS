package com.ats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Servicefunction {
    private int id;
    private String functionName;
    private List<Functionpackage> functionpackagesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicefunction that = (Servicefunction) o;
        return id == that.id &&
                Objects.equals(functionName, that.functionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, functionName);
    }

    @OneToMany(mappedBy = "servicefunctionByServiceFunctionId")
    @JsonBackReference
    public List<Functionpackage> getFunctionpackagesById() {
        return functionpackagesById;
    }

    public void setFunctionpackagesById(List<Functionpackage> functionpackagesById) {
        this.functionpackagesById = functionpackagesById;
    }
}
