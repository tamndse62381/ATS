package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.Job;
import com.ats.entity.Skillneedforjob;
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
        mapCvAndJob.put("jobList", listJob);

        List listCv = cvService.getAllCV();
        mapCvAndJob.put("cvList", listCv);

        return mapCvAndJob;
    }

    public List<VectorDTO> test() {
        List listJob = jobService.getAllCvAndJob();
        List listCv = cvService.getAllCV();

        JobDTO4 dto4 = (JobDTO4) listJob.get(0);
        return calculateVectorJobAndListCv(dto4, listCv);
    }


    @Override
    public List<VectorDTO> calculateVectorJobAndListCv(JobDTO4 job, List cvList) {
        Map<String, Integer> mapLevelSkillJobAndSkillCv = new HashMap<>();
        List<VectorDTO> listResult = new ArrayList<>();
        VectorDTO vectorDTO = new VectorDTO();


        List<Integer> listLevelOfJob = new ArrayList<>();
        List<SkillDTO2> listSkillJob = job.getListSkill();
        for (int i = 0; i < listSkillJob.size(); i++) {
            int level = listSkillJob.get(i).getLevel();
            listLevelOfJob.add(level);
        }// co dc list level cua skill

        boolean findSkill = false;

        for (int i = 0; i < cvList.size(); i++) { // duyet cv
            vectorDTO.setJob(job);
            CVDTO2 cvdto = (CVDTO2) cvList.get(i);
            List<SkillDTO2> skillListOfCv = cvdto.getSkillincvsById(); // lay duoc lít skill cua cv
            for (int j = 0; j < listSkillJob.size(); j++) {
                findSkill = false;
                SkillDTO2 skillJob = listSkillJob.get(j); //skill of job
                for (int k = 0; k < skillListOfCv.size(); k++) { // duyet list skill of cv
                    SkillDTO2 skillOfCv = skillListOfCv.get(k);
                    boolean checkSkill = checkSkillOfCvInJob(skillOfCv, skillJob); // check xem 2 skill skill job voi skill cv co giong nhau 0
                    if (checkSkill == true) {
                        mapLevelSkillJobAndSkillCv.put(skillJob.getSkillName() + skillJob.getLevel(), skillOfCv.getLevel());
                        findSkill = true;
                        break;
                    }
                }
                if (findSkill == false) {
                    mapLevelSkillJobAndSkillCv.put(skillJob.getSkillName() + skillJob.getLevel(), 0);
                }
            }
            // duyet xong cac skill can cho job
            //ra duoc 1 value vector giua job and cv do
            double resultVectorOfJobAndCv = calculateVectorWithMapValue(mapLevelSkillJobAndSkillCv);
            vectorDTO.setCvId(cvdto.getId());
            vectorDTO.setVectorJobAndCv(resultVectorOfJobAndCv);

            listResult.add(vectorDTO);
            vectorDTO = new VectorDTO();
            mapLevelSkillJobAndSkillCv = new HashMap<>();
        }

        return listResult;
    }

    public boolean checkSkillOfCvInJob(SkillDTO2 skillOfCv, SkillDTO2 skillOfJob) {
        if (skillOfCv.getSkillName().equals(skillOfJob.getSkillName())) {
            return true;
        }
        return false;
    }

    public double calculateVectorWithMapValue(Map<String, Integer> mapLevelSkillJobAndSkillCv) {
        double a = 0; // so bi chia
        double b = 0; // so chia
        double totalKeys = 0;
        double totalValues = 0;
        for (Map.Entry<String, Integer> maps : mapLevelSkillJobAndSkillCv.entrySet()) {
            double levelSkillOfJob = maps.getKey().charAt(maps.getKey().length() - 1);
            a = a + (levelSkillOfJob * maps.getValue());
            totalKeys = totalKeys + Math.pow(levelSkillOfJob, 2);
            totalValues = totalValues + Math.pow((double) maps.getValue(), 2);
        }
        b = Math.sqrt(totalKeys) * Math.sqrt(totalValues);

        Double result = a / b;
        if (result.isNaN()) {
            return -1;
        }
        return result;
    }


}
