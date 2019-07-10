package com.ats.service.impl;

import com.ats.dto.*;
import com.ats.entity.*;
import com.ats.form.CreateCVForm;
import com.ats.form.GetCVForm;
import com.ats.model.FileModel;
import com.ats.repository.CVRepository;
import com.ats.repository.CityRepository;
import com.ats.repository.IndustryRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.*;
import com.ats.util.FileUltis;
import com.ats.util.RestResponse;
import org.hibernate.hql.internal.ast.tree.RestrictableStatement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    public RestResponse getCVByCVID(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv == null)
            return new RestResponse(false,"CV này không tồn tại!!!", null);
        return new RestResponse(true, "Tải CV thành công!!!", cv);
    }

    @Override
    public Cv getCvByEmail() {
           return cvRepository.findCVByEmail(EMAIL);
    }

    @Override
    public RestResponse create(CVDTO newCV) {
        try {
            Cv cv = new Cv();
            cv = modelMapper.map(newCV, Cv.class);
            cv.setEmail(EMAIL);
            cv.setCityByCityId(cityRepository.findOne(newCV.getCityId()));
            cv.setIndustryByIndustryId(industryRepository.findOne(newCV.getIndustryId()));
            cv.setUsersByUserId(usersRepository.findOne(newCV.getUserId()));

            // check đã có CV nào được Active tìm việc chưa
            if (checkIsActive(newCV.getUserId()))
                cv.setIsActive(1);
            else
                cv.setIsActive(0);
            cv.setStatus("1");
            cv.setCreatedDate(new Timestamp(new Date().getTime()));
            cvRepository.save(cv);
            Cv changeEmailCv = getCvByEmail();
            int CVID = changeEmailCv.getId();
            changeEmailCv.setEmail(newCV.getEmail());
            cvRepository.save(changeEmailCv);

            // Save vo
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
            return new RestResponse(true, "Bạn đã tạo CV thành công!!!", null);
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return new RestResponse(false, "Tạo CV không thành công. Vui lòng thử lại!!!", null);
    }

    @Override
    public RestResponse edit(CVDTO editedCv) {
        Cv cv = cvRepository.findOne(editedCv.getId());
        if (cv == null)
            return new RestResponse(false,"Không thành công!!!", null);

        return new RestResponse(true, "Chỉnh sửa thành công!!!", null);
    }

    @Override
    public RestResponse delete(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv == null)
            return new RestResponse(false, "Không tìm thấy CV có Id: " + id, null);
        cv.setStatus("2");
        cv.setIsActive(0);
        cvRepository.save(cv);
        return new RestResponse(true, "Đã xoá CV thành công!!!", null);
    }

    @Override
    public RestResponse getlistCvByUserId(int id) {
        Users user = usersRepository.findOne(id);
        if (user == null)
            return new RestResponse(false, "Không tìm thấy người dùng có ID là: " + id, null);
        List<Cv> list =  cvRepository.findByUserIdAndStatus(id, "1");
        if (list == null)
            return new RestResponse(false, "Bạn chưa tạo CV!!!", null);
        return new RestResponse(true, "", list);
    }

    @Override
    public RestResponse checkActive(int id) {
        List<Cv> list1 = cvRepository.findByUserId(id);
        if (list1 == null)
            return new RestResponse(true, "Bạn chưa tạo CV nào!!!", null);
        List<Cv> list = cvRepository.checkActive(id, 1);
        if (list == null)
            return new RestResponse(true, "Bạn chưa chọn CV nào là Cv chính của mình. Hãy đặt CV chính để không bỏ lỡ cơ hội tìm việc nhé!!!", null);
        return null;
    }

    @Override
    public RestResponse setMainCv(int id) {
        Cv cv = cvRepository.findOne(id);
        if (cv == null)
            return new RestResponse(false, "Có lỗi!!!!", null);
        List<Cv> list = cvRepository.findByUserId(cv.getUserId());
        for (Cv cv1 : list) {
            cv1.setIsActive(0);
            cvRepository.save(cv1);
        }
        cv.setIsActive(1);
        cvRepository.save(cv);
        return new RestResponse(true, "Cập nhật thành công!!!", null);
    }

    private boolean checkIsActive(int userId){
        List<Cv> list = cvRepository.checkIsActive(userId, 1);
        if (list.isEmpty())
            return true;
        return false;
    }
}
