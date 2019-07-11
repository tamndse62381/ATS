package com.ats.service.impl;

import  com.ats.entity.Apply;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.ApplyRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.ApplyService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class ApplyServiceImpl implements ApplyService  {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private

    @Override
    public RestResponse create(int CvId, int JobID) {
        Users  user = usersRepository.findOne(JobSeekerID);
        if (user == null)
            return new RestResponse(false, "Lỗi: người dùng không tồn tại!", null);
        Job job = jobRepository.findOne(JobID);
        if (job == null)
            return new RestResponse(false, "Lỗi: việc làm này không tồn tại!", null);
        if (!check(JobSeekerID, JobID))
        {
            Apply apply = new Apply();
            apply.setJobId(JobID);
            apply.setId(JobSeekerID);
            apply.setDayApply(new Timestamp(new Date().getTime()));
            apply.setStatus("1");
            applyRepository.save(apply);
            return new RestResponse(true,"Bạn đã ứng tuyển vào công việc này thành công!!!", null);
        }
        return new RestResponse(false, "Ứng tuyển không thành công. Vui lòng thử lại!!!", null);
    }

    @Override
    public RestResponse confirm(int ApplyId) {
        Apply apply = applyRepository.findOne(ApplyId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("2");
        applyRepository.save(apply);
        // goi mail serviec gui mail cho employer
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public RestResponse deny(int ApplyId) {
        Apply apply = applyRepository.findOne(ApplyId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("3");
        applyRepository.save(apply);
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public boolean check(int JobSeekerID, int JobID) {
        Apply apply = applyRepository.findApplyBy(JobSeekerID, JobID);
        if (apply != null)
            return true;
        return false;
    }
}
