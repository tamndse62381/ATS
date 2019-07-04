package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.*;
import com.ats.model.FileModel;
import com.ats.repository.CVRepository;
import com.ats.repository.CityRepository;
import com.ats.repository.IndustryRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.*;
import com.ats.util.FileUltis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import java.io.File;
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
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    HttpServletRequest httpServletRequest;

    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();
    // Conts
    private static String EMAIL = "1101010001001101000000000000";

    @Override
    public ResponseEntity<Cv> getCVByCVID(int id) {
        Cv cv = cvRepository.findById(id).orElseThrow(() -> new
                NotFoundException(""));
        return ResponseEntity.ok(cv);
    }

    @Override
    public Cv getCvByEmail() {
           return cvRepository.findCVByEmail(EMAIL);
    }

    @Override
    public boolean create(CVDTO newCV, FileModel file) {
        try {
            Cv cv = new Cv();
            cv = modelMapper.map(newCV, Cv.class);
            cv.setEmail(EMAIL);
            cv.setCityByCityId(cityRepository.findOne(newCV.getCityId()));
            cv.setIndustryByIndustryId(industryRepository.findOne(newCV.getIndustryId()));
            cv.setUsersByUserId(usersRepository.findOne(newCV.getUserId()));
            //updaload file
            String uploadPath = httpServletRequest.getRealPath("") + File.separator + "hinhanh" + File.separator;
            //set img
            String fileName = FileUltis.saveFile(file, uploadPath);
            cv.setImg(fileName);
            cv.setStatus("1");
            cv.setIsActive(1);
            cv.setCreatedDate(new Timestamp(new Date().getTime()));
            cvRepository.save(cv);
            Cv changeEmailCv = getCvByEmail();
            int CVID = changeEmailCv.getId();
            changeEmailCv.setEmail(newCV.getEmail());
            cvRepository.save(changeEmailCv);

            Cv saveCv = cvRepository.findOne(CVID);
            // mapping Certification
            List<CertificationDTO> listCer = newCV.getCertificationsById();
            if (listCer != null){
                for (CertificationDTO certificationDTO : listCer) {
                    Certification cer = modelMapper.map(certificationDTO, Certification.class);
                    cer.setCvid(CVID);
                    cer.setCvByCvid(saveCv);
                    certificationService.createANewCertification(cer);
                }
            }
            // mapping Education
            List<EducationDTO> listEdu = newCV.getEducationsById();
            if (listEdu != null){
                for (EducationDTO educationDTO : listEdu) {
                    Education edu = modelMapper.map(educationDTO, Education.class);
                    edu.setCvid(CVID);
                    edu.setCvByCvid(saveCv);
                    educationService.createANewEducation(edu);
                }
            }
            // mapping SocialActivity
            List<SocialactivitiesDTO> listAct = newCV.getSocialactivitiesById();
            if (listAct != null){
                for (SocialactivitiesDTO socialactivitiesDTO : listAct) {
                    Socialactivities soc = modelMapper.map(socialactivitiesDTO, Socialactivities.class);
                    soc.setCvid(CVID);
                    soc.setCvByCvid(saveCv);
                    socialactivityService.createANewSocialactivity(soc);
                }
            }
            // mapping WorkExperience
            List<WorkexperienceDTO> listWor = newCV.getWorkexperiencesById();
            if (listWor != null){
                for (WorkexperienceDTO workexperienceDTO : listWor) {
                    Workexperience wor = modelMapper.map(workexperienceDTO, Workexperience.class);
                    wor.setCvid(CVID);
                    wor.setCvByCvid(saveCv);
                    workexperienceService.createANewWorkExperience(wor);
                }
            }
            //mapping ProjectOrProduct
            List<ProjectorproductworkedDTO> listPro = newCV.getProjectorproductworkedsById();
            if (listPro != null){
                for (ProjectorproductworkedDTO projectorproductworkedDTO : listPro) {
                    Projectorproductworked pro = modelMapper.map(projectorproductworkedDTO, Projectorproductworked.class);
                    pro.setCvid(CVID);
                    pro.setCvByCvid(saveCv);
                    projectorproductworkedService.createANewProjectorProduct(pro);
                }
            }
            return true;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean createTemp(CVDTO newCV) {
        try {
            Cv cv = new Cv();
            cv = modelMapper.map(newCV, Cv.class);
            cv.setEmail(EMAIL);
            cv.setCityByCityId(cityRepository.findOne(newCV.getCityId()));
            cv.setIndustryByIndustryId(industryRepository.findOne(newCV.getIndustryId()));
            cv.setUsersByUserId(usersRepository.findOne(newCV.getUserId()));
            cv.setStatus("1");
            cv.setIsActive(1);
            cv.setCreatedDate(new Timestamp(new Date().getTime()));
            cvRepository.save(cv);
            Cv changeEmailCv = getCvByEmail();
            int CVID = changeEmailCv.getId();
            changeEmailCv.setEmail(newCV.getEmail());
            cvRepository.save(changeEmailCv);

            Cv saveCv = cvRepository.findOne(CVID);
            // mapping Certification
            List<CertificationDTO> listCer = newCV.getCertificationsById();
            if (listCer != null){
                for (CertificationDTO certificationDTO : listCer) {
                    Certification cer = modelMapper.map(certificationDTO, Certification.class);
                    cer.setCvid(CVID);
                    cer.setCvByCvid(saveCv);
                    certificationService.createANewCertification(cer);
                }
            }
            // mapping Education
            List<EducationDTO> listEdu = newCV.getEducationsById();
            if (listEdu != null){
                for (EducationDTO educationDTO : listEdu) {
                    Education edu = modelMapper.map(educationDTO, Education.class);
                    edu.setCvid(CVID);
                    edu.setCvByCvid(saveCv);
                    educationService.createANewEducation(edu);
                }
            }
            // mapping SocialActivity
            List<SocialactivitiesDTO> listAct = newCV.getSocialactivitiesById();
            if (listAct != null){
                for (SocialactivitiesDTO socialactivitiesDTO : listAct) {
                    Socialactivities soc = modelMapper.map(socialactivitiesDTO, Socialactivities.class);
                    soc.setCvid(CVID);
                    soc.setCvByCvid(saveCv);
                    socialactivityService.createANewSocialactivity(soc);
                }
            }
            // mapping WorkExperience
            List<WorkexperienceDTO> listWor = newCV.getWorkexperiencesById();
            if (listWor != null){
                for (WorkexperienceDTO workexperienceDTO : listWor) {
                    Workexperience wor = modelMapper.map(workexperienceDTO, Workexperience.class);
                    wor.setCvid(CVID);
                    wor.setCvByCvid(saveCv);
                    workexperienceService.createANewWorkExperience(wor);
                }
            }
            //mapping ProjectOrProduct
            List<ProjectorproductworkedDTO> listPro = newCV.getProjectorproductworkedsById();
            if (listPro != null){
                for (ProjectorproductworkedDTO projectorproductworkedDTO : listPro) {
                    Projectorproductworked pro = modelMapper.map(projectorproductworkedDTO, Projectorproductworked.class);
                    pro.setCvid(CVID);
                    pro.setCvByCvid(saveCv);
                    projectorproductworkedService.createANewProjectorProduct(pro);
                }
            }
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

    @Override
    public boolean delete(int id) {
        Cv cv = cvRepository.findById(id).orElseThrow(()-> new NotFoundException("Can't find Cv have Id: " + id));
        cv.setStatus("2");
        cv.setIsActive(0);
        return true;
    }
}
