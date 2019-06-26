package com.ats.service.impl;

import com.ats.entity.Countcv;
import com.ats.entity.Cv;
import com.ats.entity.Users;
import com.ats.repository.CVRepository;
import com.ats.repository.CountcvRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.CountcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CountcvServiceImpl implements CountcvService {
    @Autowired
    private CountcvRepository countcvRepository;
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean countWhenEmployerGetDetailOfCV(int CVID, int EmployerID) {
        Cv cv = cvRepository.findById(CVID).orElseThrow(()->
                new NotFoundException("Not fount Cv have ID: " + CVID));
        Users user = usersRepository.findById(EmployerID).orElseThrow(() ->
                new NotFoundException("Not found Employer have ID: " + EmployerID));
        Countcv count = countcvRepository.findCountcv(CVID, EmployerID);
        if (count == null){
            count.setCvid(CVID);
            count.setUserid(EmployerID);
            count.setCreatedDate(new Timestamp(new Date().getTime()));
            countcvRepository.save(count);
            return true;
        }
        return false;
    }

    @Override
    public int countAccessTimes(int JobSeekerId) {
        List<Cv> listCv = cvRepository.findByUserid(JobSeekerId);
        int count = 0;
        for (Cv cv : listCv) {
            count =+ countcvRepository.countCountcvsByCvid(cv.getId());
        }
        return count;
    }
}
