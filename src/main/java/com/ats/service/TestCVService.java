package com.ats.service;

import com.ats.dto.JobDTO2;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestCVService {
    RestResponse suggest();
}
