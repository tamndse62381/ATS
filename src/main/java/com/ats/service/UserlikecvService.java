package com.ats.service;

import com.ats.util.RestResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserlikecvService {
    boolean check(int EmployerId, int Cvid);

    RestResponse create(int EmployerId, int CvId);

    RestResponse listCv(int EmployerId);

    RestResponse unCheck(int EmployerId, int CvId);
}