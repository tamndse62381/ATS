package com.ats.ws;

import com.ats.dto.CVDTO;
import com.ats.entity.*;
import com.ats.repository.CVRepository;
import com.ats.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVWS {
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

    // Get CV By CVID
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<CVDTO> getCV(@RequestParam("id") int CVID){
        Cv cv = cvRepository.findOne(CVID);
        if (cv != null) {
            List<Certification> listCer = certificationService.getListCertificationByCVID(CVID);
            List<Education> listEdu = educationService.getListEduByCVId(CVID);
            List<Workexperience> listWork = workexperienceService.getByCVID(CVID);
            List<Socialactivities> listSoc = socialactivityService.findListSocialactivityByCVID(CVID);
            List<Projectorproductworked> listPro = projectorproductworkedService.getListProjectByCVID(CVID);

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

    // Create A New CV
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<CVDTO> createANewCV(@RequestBody CVDTO newCV){
        Cv cv = new Cv();
        cv.setEmail(EMAIL);
        cvRepository.save(cv);
        cv = cvRepository.findCVByCVID(EMAIL);

        // mapping CV
        cv.setTitle(newCV.getTitle());
        cv.setUserID(newCV.getUserid());
        cv.setImg(newCV.getImg());
        cv.setEmail(newCV.getEmail());
        cv.setFirstName(newCV.getFirstName());
        cv.setLastName(newCV.getLastName());
        cv.setGender(newCV.getGender());
        cv.setDob(new Timestamp(newCV.getDob().getTime()));
        cv.setCityID(newCV.getCityid());
        cv.setAddress(newCV.getAddress());
        cv.setIndustryID(newCV.getIndustryid());
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
                certification.setCVID(CVID);
                certificationService.createANewCertification(certification);
            }
        }


        // mapping Education
        List<Education> listEdu = newCV.getEducations();
        if (listEdu != null){
            for (Education education : listEdu) {
                education.setCVID(CVID);
                educationService.createANewEducation(education);
            }
        }

        // mapping SocialActivity
        List<Socialactivities> listAct = newCV.getSocialactivities();
        if (listAct != null){
            for (Socialactivities socialactivities : listAct) {
                socialactivities.setCVID(CVID);
                socialactivityService.createANewSocialactivity(socialactivities);
            }
        }

        // mapping WorkExperience
        List<Workexperience> listWor = newCV.getWorkexperiences();
        if (listWor != null){
            for (Workexperience workexperience : listWor) {
                workexperience.setCVID(CVID);
                workexperienceService.createANewWorkExperience(workexperience);
            }
        }

        //mapping ProjectOrProduct
        List<Projectorproductworked> listPro = newCV.getProjectorproductworkeds();
        if (listPro != null){
            for (Projectorproductworked projectorproductworked : listPro) {
                projectorproductworked.setCVID(CVID);
                projectorproductworkedService.createANewProjectorProduct(projectorproductworked);
            }
        }

        // Save to DB
        cvRepository.save(cv);
        return null;
    }

    // Delete One CV
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<CVDTO> deleteACV(@RequestBody CVDTO deletedCVDTO){
        return null;
    }

}
