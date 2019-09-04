package com.ats.service;


import com.ats.dto.CVDTO2;

import java.util.List;
import java.util.Map;

public interface SuggestService {
    Map<String, List> getListCvAndJob();
    List<CVDTO2> standardizedListCv(List<CVDTO2> listCv);
}
