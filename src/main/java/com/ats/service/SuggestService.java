package com.ats.service;


import com.ats.dto.CVDTO2;
import com.ats.dto.JobDTO4;
import com.ats.dto.VectorDTO;
import com.ats.entity.Job;

import java.util.List;
import java.util.Map;

public interface SuggestService {
    Map<String, List> getListCvAndJob();

    List<VectorDTO>calculateVectorJobAndListCv(JobDTO4 job,List cvList);

    List<VectorDTO> test();

}
