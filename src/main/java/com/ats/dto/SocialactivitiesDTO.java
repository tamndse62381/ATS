package com.ats.dto;


import java.io.Serializable;

public class SocialactivitiesDTO implements Serializable {
    private Integer id;
    private Integer cvid;
    private String name;
    private String description;

    public SocialactivitiesDTO() {
    }

    public SocialactivitiesDTO(Integer id, Integer cvid, String name, String description) {
        this.id = id;
        this.cvid = cvid;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
