package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.Cv;
import com.ats.entity.Job;
import com.ats.entity.Skillincv;
import com.ats.entity.Skillneedforjob;
import com.ats.service.JobService;
import com.ats.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List resultCalculateVectorJobAndListCV = getListCvByVector(calculateVectorJobAndListCv(dto4, listCv));// lay list cv
        //return calculateVectorJobAndListCv(dto4, listCv);
       // return calculateLenghtOfVectorJobAndListCv(dto4, resultCalculateVectorJobAndListCV);
        return averageLenghtAndEdge(calculateLenghtOfVectorJobAndListCv(dto4, resultCalculateVectorJobAndListCV),calculateVectorJobAndListCv(dto4, listCv));
    }

    public List getListCvByVector(List<VectorDTO> listVector) { // lay ra nhung thang co vector > 0 trong list vector
        List cvList = new ArrayList();
        for (int i = 0 ; i < listVector.size(); i++){
            VectorDTO vectorDTO=listVector.get(i);
            if(vectorDTO.getVectorJobAndCv() > 0){
                int idCv = vectorDTO.getCvId(); // id cua cv tra ve cv
                Cv cv = cvService.getCVByID(idCv);
                cvList.add(cv);
            }
        }
        return cvList;

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
            Cv cvdto = (Cv) cvList.get(i);
            List<Skillincv> skillincvs = cvdto.getSkillincvsById();
            List<SkillDTO2> skillListOfCv = new ArrayList<>();
            for (int j = 0; j < skillincvs.size(); j++) {
                SkillDTO2 dto2 = new SkillDTO2();
                dto2.setSkillName(skillincvs.get(j).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
                dto2.setLevel(skillincvs.get(j).getSkillBySkillId().getSkillLevel());
                skillListOfCv.add(dto2);
            } // lay duoc lít skill cua cv
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

    @Override
    public List<VectorDTO> calculateLenghtOfVectorJobAndListCv(JobDTO4 job, List cvList) {
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
            Cv cvdto = (Cv) cvList.get(i);
            List<Skillincv> skillincvs = cvdto.getSkillincvsById();
            List<SkillDTO2> skillListOfCv = new ArrayList<>();
            for (int j = 0; j < skillincvs.size(); j++) {
                SkillDTO2 dto2 = new SkillDTO2();
                dto2.setSkillName(skillincvs.get(j).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
                dto2.setLevel(skillincvs.get(j).getSkillBySkillId().getSkillLevel());
                skillListOfCv.add(dto2);
            }
           // List<SkillDTO2> skillListOfCv = cvdto.getSkillincvsById(); // lay duoc lít skill cua cv
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
                    //  mapLevelSkillJobAndSkillCv.put(skillJob.getSkillName() + skillJob.getLevel(), 0);
                }
            }
            // duyet xong cac skill can cho job
            //ra duoc 1 value vector giua job and cv do
            double resultVectorOfJobAndCv = calculateLenghtOfVectorWithMapValue(mapLevelSkillJobAndSkillCv);
            vectorDTO.setCvId(cvdto.getId());
            //  vectorDTO.setVectorJobAndCv(resultVectorOfJobAndCv);
            vectorDTO.setLenghtVectorCV(resultVectorOfJobAndCv);

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

    public double calculateLenghtOfVectorWithMapValue(Map<String, Integer> mapLevelSkillJobAndSkillCv) {
        double result = 0;
        double totalValues = 0;
        for (Map.Entry<String, Integer> maps : mapLevelSkillJobAndSkillCv.entrySet()) {
            totalValues = totalValues + Math.pow((double) maps.getValue(), 2);

        }
        result = Math.sqrt(totalValues);
        return result;
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

    @Override
    public List<VectorDTO> averageLenghtAndEdge(List<VectorDTO> lenght, List<VectorDTO> edge){
        List<VectorDTO> result = new ArrayList<>();
        List<VectorDTO> tmpEdge = new ArrayList<>();
        for (int i = 0 ; i < edge.size(); i++){
            VectorDTO vectorDTO = edge.get(i);
            if(vectorDTO.getVectorJobAndCv() > 0){
                tmpEdge.add(vectorDTO);
            }
        }
     // sort list độ dài
        Collections.sort(lenght, new Comparator<VectorDTO>() {
            @Override
            public int compare(VectorDTO lhs, VectorDTO rhs) {
                // -1 - less than, 1 - greater than, 0 - equal từ lớn tới nhỏ
                return lhs.getLenghtVectorCV() > rhs.getLenghtVectorCV() ? -1 : (lhs.getLenghtVectorCV() < rhs.getLenghtVectorCV()) ? 1 : 0;
            }
        });
        // sort list góc
        Collections.sort(tmpEdge, new Comparator<VectorDTO>() {
            @Override
            public int compare(VectorDTO lhs, VectorDTO rhs) {
                // -1 - less than, 1 - greater than, 0 - equal từ nhỏ tới lớn
                return lhs.getVectorJobAndCv() > rhs.getVectorJobAndCv() ? -1 : (lhs.getVectorJobAndCv() < rhs.getVectorJobAndCv()) ? 1 : 0;
            }
        });

        result.addAll(lenght);
        for(int i = 0; i < result.size(); i ++){
            int cvid = result.get(i).getCvId();// lấy cv id
            double lg = i+1;
            double ed = 0;

            for (int j = 0; j < tmpEdge.size()  ; j++ ){// tìm cv by cvid của list góc
                if(tmpEdge.get(j).getCvId() == cvid){
                    ed = j + 1;
                }
            }
            result.get(i).setPositionLength(lg);
            result.get(i).setPositionVector(ed);
            result.get(i).setAverage((lg + ed) / 2);// tính trung bình số thứ tự cho mỗi record
        }
        // sort list kết quả
        Collections.sort(result, new Comparator<VectorDTO>() {
            @Override
            public int compare(VectorDTO lhs, VectorDTO rhs) {
                // -1 - less than, 1 - greater than, 0 - equal từ nhỏ tới lớn
                return lhs.getAverage() < rhs.getAverage() ? -1 : (lhs.getAverage() > rhs.getAverage()) ? 1 : 0;
            }
        });
        return result;
    }


//do dai dau
}
