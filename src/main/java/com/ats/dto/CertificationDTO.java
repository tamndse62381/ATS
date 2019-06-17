package com.ats.dto;

import java.io.Serializable;

public class CertificationDTO implements Serializable {
    private Integer id;
    private Integer cvid;
    private String certificationName;

    public CertificationDTO() {
    }

    public CertificationDTO(Integer id, Integer cvid, String certificationName) {
        this.id = id;
        this.cvid = cvid;
        this.certificationName = certificationName;
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

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }
}
