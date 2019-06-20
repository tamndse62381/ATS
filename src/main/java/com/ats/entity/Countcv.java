package com.ats.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Countcv {
    private int id;
    private int userId;
    private int cvid;
    private Timestamp createdDate;
    private Users usersByUserId;
    private Cv cvByCvid;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = false , insertable = false , updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CVID", nullable = false , insertable = false , updatable = false)
    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Countcv countcv = (Countcv) o;
        return id == countcv.id &&
                userId == countcv.userId &&
                cvid == countcv.cvid &&
                Objects.equals(createdDate, countcv.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cvid, createdDate);
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
    @JoinColumn(name = "CVID", referencedColumnName = "ID", nullable = false)
    public Cv getCvByCvid() {
        return cvByCvid;
    }

    public void setCvByCvid(Cv cvByCvid) {
        this.cvByCvid = cvByCvid;
    }
}
