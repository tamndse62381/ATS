package com.ats.service.impl;

import com.ats.dto.CVDTO;
import com.ats.entity.*;
import com.ats.repository.CVRepository;
import com.ats.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private EducationService educationService;
    @Autowired
    private CertificationService certificationService;
    @Autowired
    private WorkexperienceService workexperienceService;
    @Autowired
    private SocialactivityService socialactivityService;
    @Autowired
    private ProjectorproductworkedService projectorproductworkedService;
    ModelMapper modelMapper = new ModelMapper();
    // Conts
    private static String EMAIL = "1101010001001101000000000000";
    @Override
    public ResponseEntity<CVDTO> getCVByCVID(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv != null) {
            List<Certification> listCer = certificationService.getListCertificationByCVID(id);
            List<Education> listEdu = educationService.getListEduByCVId(id);
            List<Workexperience> listWork = workexperienceService.getByCVID(id);
            List<Socialactivities> listSoc = socialactivityService.findListSocialactivityByCVID(id);
            List<Projectorproductworked> listPro = projectorproductworkedService.getListProjectByCVID(id);

            CVDTO returnCVDTO = modelMapper.map(cv, CVDTO.class);
            returnCVDTO.setCertifications(listCer);
            returnCVDTO.setSocialactivities(listSoc);
            returnCVDTO.setWorkexperiences(listWork);
            returnCVDTO.setProjectorproductworkeds(listPro);
            returnCVDTO.setEducations(listEdu);
            return ResponseEntity.ok().body(returnCVDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public Cv getCvByEmail(String email) {
//        return cvRepository.find(email);
        return null;
    }

    @Override
    public boolean create(CVDTO newCV) {
        try {
            Cv cv = new Cv();
            cv.setEmail(EMAIL);
            cvRepository.save(cv);
            cv = cvRepository.findCVByCVID(EMAIL);
            // mapping CV
            cv.setTitle(newCV.getTitle());
            cv.setUserId(newCV.getUserid());
            cv.setImg(newCV.getImg());
            cv.setEmail(newCV.getEmail());
            cv.setFirstName(newCV.getFirstName());
            cv.setLastName(newCV.getLastName());
            cv.setGender(newCV.getGender());
            cv.setDob(new Timestamp(newCV.getDob().getTime()));
            cv.setCityId(newCV.getCityid());
            cv.setAddress(newCV.getAddress());
            cv.setIndustryId(newCV.getIndustryid());
            cv.setDescription(newCV.getDescription());
            cv.setYearExperience(newCV.getYearExperience());
            cv.setSalaryFrom(newCV.getSalaryFrom());
            cv.setSalaryTo(newCV.getSalaryTo());
            cv.setStatus(newCV.getStatus());
            cv.setCreatedDate(new Timestamp(new Date().getTime()));
            cv.setIsActive(1);
            // get CVID
            int CVID = cv.getId();
            // mapping Certification
            List<Certification> listCer = newCV.getCertifications();
            if (listCer != null){
                for (Certification certification : listCer) {
                    certification.setCvid(CVID);
                    certificationService.createANewCertification(certification);
                }
            }
            // mapping Education
            List<Education> listEdu = newCV.getEducations();
            if (listEdu != null){
                for (Education education : listEdu) {
                    education.setCvid(CVID);
                    educationService.createANewEducation(education);
                }
            }

            // mapping SocialActivity
            List<Socialactivities> listAct = newCV.getSocialactivities();
            if (listAct != null){
                for (Socialactivities socialactivities : listAct) {
                    socialactivities.setCvid(CVID);
                    socialactivityService.createANewSocialactivity(socialactivities);
                }
            }
            // mapping WorkExperience
            List<Workexperience> listWor = newCV.getWorkexperiences();
            if (listWor != null){
                for (Workexperience workexperience : listWor) {
                    workexperience.setCvid(CVID);
                    workexperienceService.createANewWorkExperience(workexperience);
                }
            }

            //mapping ProjectOrProduct
            List<Projectorproductworked> listPro = newCV.getProjectorproductworkeds();
            if (listPro != null){
                for (Projectorproductworked projectorproductworked : listPro) {
                    projectorproductworked.setCvid(CVID);
                    projectorproductworkedService.createANewProjectorProduct(projectorproductworked);
                }
            }
            // Save to DB
            cvRepository.save(cv);
            return true;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean edit(CVDTO editedCv) {
        Cv cv =cvRepository.findById(editedCv.getId()).orElseThrow(
                () -> new NotFoundException(editedCv.getId() + "Cv Not found")
        );


        return false;
    }
}
