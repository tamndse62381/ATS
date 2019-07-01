package com.ats.dto;

import java.io.Serializable;

public class CertificationDTO implements Serializable {
    private int id;
    private int cvid;
    private String certificationName;

    public CertificationDTO() {
    }

    public CertificationDTO(int id, int cvid, String certificationName) {
        this.id = id;
        this.cvid = cvid;
        this.certificationName = certificationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }
}
