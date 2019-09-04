package com.ats.service.impl;

import com.ats.dto.CVDTO2;
import com.ats.dto.SkillDTO2;
import com.ats.service.JobService;
import com.ats.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    JobServiceImpl jobService;

    @Autowired
    CVServiceImpl cvService;

    @Override
    public Map<String, List> getListCvAndJob() {
        Map<String, List> mapCvAndJob = new HashMap<>();
        List listJob = jobService.getAllCvAndJob();
        List listCv = cvService.getAllCV();

        mapCvAndJob.put("jobList", listJob);

        List listCvStandardized=standardizedListCv(listCv);
        mapCvAndJob.put("cvList", listCvStandardized);

        return mapCvAndJob;
    }

    @Override
    public List<CVDTO2> standardizedListCv(List<CVDTO2> listCv) {

        List<CVDTO2> listCvStandardized=new ArrayList<>();

        for (int i = 0; i < listCv.size(); i++) {
            CVDTO2 cvdto2 = listCv.get(i);
            List<SkillDTO2> skillDTOOfCv=standardizedSkillLevelOfCv(cvdto2);
            cvdto2.setSkillincvsById(skillDTOOfCv);
            listCvStandardized.add(cvdto2);
        }
        return listCvStandardized;
    }

    public List<SkillDTO2> standardizedSkillLevelOfCv(CVDTO2 cvdto2) {
        float avergeLevelSkill = 0;
        float sumLevel = 0;

        List<SkillDTO2> skillListOfCv = cvdto2.getSkillincvsById();

        //for get averge
        for (int i = 0; i < skillListOfCv.size(); i++) {
            SkillDTO2 skillDTO2 = skillListOfCv.get(i);
            float level = skillDTO2.getLevel();
            sumLevel = sumLevel + level;
        }
        avergeLevelSkill = (float) sumLevel / (float) skillListOfCv.size();

        //for chuan hoa level
        for (int i = 0; i < skillListOfCv.size(); i++) {
            SkillDTO2 skillDTO2 = skillListOfCv.get(i);
            float oldLevelOfSkill = skillDTO2.getLevel();

            float newLevelOfSkill=oldLevelOfSkill-avergeLevelSkill;
            skillDTO2.setLevel(newLevelOfSkill);
        }
        return skillListOfCv;
    }
}
