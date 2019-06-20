package com.ats.dto;

import java.io.Serializable;
import java.util.Date;

public class EducationDTO implements Serializable {
    private Integer id;
    private Integer cvid;
    private String schooltype;
    private String major;
    private Date starttime;
    private Date endtime;
    private String description;

    public EducationDTO() {
    }

    public EducationDTO(Integer id, Integer cvid, String schooltype, String major, Date starttime, Date endtime, String description) {
        this.id = id;
        this.cvid = cvid;
        this.schooltype = schooltype;
        this.major = major;
        this.starttime = starttime;
        this.endtime = endtime;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvid() {
        return cvid;
    }

    public void setCvid(Integer cvid) {
        this.cvid = cvid;
    }

    public String getSchooltype() {
        return schooltype;
    }

    public void setSchooltype(String schooltype) {
        this.schooltype = schooltype;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
