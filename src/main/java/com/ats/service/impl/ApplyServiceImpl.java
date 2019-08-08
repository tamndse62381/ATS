package com.ats.service.impl;

import com.ats.entity.*;
import com.ats.repository.ApplyRepository;
import com.ats.repository.CVRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.ApplyService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService{
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CVRepository cvRepository;

    @Override
    public RestResponse create(int CvId, int JobID) {
        Job job = jobRepository.findOne(JobID);
        Cv cv = cvRepository.findOne(CvId);
        if (job == null || cv == null)
            return new RestResponse(false, "Lỗi: việc làm này không tồn tại!", null);
        Apply apply = new Apply();
        apply.setJobId(JobID);
        apply.setCvid(CvId);
        apply.setCvByCvid(cv);
        apply.setJobByJobId(job);
        apply.setDayApply(new Timestamp(new Date().getTime()));
        apply.setStatus("1");
        applyRepository.save(apply);
        return new RestResponse(true,"Bạn đã ứng tuyển vào công việc này thành công!!!", null);
    }

    @Override
    public RestResponse confirm(int JobId, int CvId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("2");
        applyRepository.save(apply);
        // goi mail serviec gui mail cho employer
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public RestResponse deny(int JobId, int CvId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra. Vui lòng thử lại!!!", null);
        apply.setStatus("3");
        applyRepository.save(apply);
        return new RestResponse(true, "Thành công!!!", null);
    }

    @Override
    public boolean check(int JobSeekerID, int JobID) {
        List<Cv> listCv = cvRepository.findByUserId(JobSeekerID);
        if (listCv == null)
            return true;
        for (Cv cv : listCv) {
            if (applyRepository.findForCheck(cv.getId(), JobID) != null)
                return true;
        }
        return false;
    }

    @Override
    public RestResponse listJob(int JobSeekerId) {
        List<Cv> listCv = cvRepository.findByUserId(JobSeekerId);
        if (listCv == null)
            return new RestResponse(false, "Bạn chưa ứng tuyển công việc nào!!!", null);
        List<Job> listJob = new ArrayList<>();
        for (Cv cv : listCv) {
            List<Apply> list = applyRepository.findAppliesByCvid(cv.getId());
            for (Apply apply : list) {
                listJob.add(apply.getJobByJobId());
            }
        }
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public Page<Cv> listCv(int JobId, Pageable pageable) {
        Job job = jobRepository.findOne(JobId);
        if (job == null)
            return null;
        List<Apply> listApply = applyRepository.findAppliesByJobId(JobId);
        if (listApply == null)
            return null;
        List<Cv> listCv = new ArrayList<>();
        for (Apply apply : listApply) {
            listCv.add(apply.getCvByCvid());
        }
        Page<Cv> pageCv = new PageImpl<>(listCv, pageable, listCv.size());
        return pageCv;
    }

    @Override
    public RestResponse checkStatusApply(int CvId, int JobId) {
        Apply apply = applyRepository.findForCheck(CvId, JobId);
        if (apply == null)
            return new RestResponse(false, "Có lỗi xảy ra vui lòng thử lại!!!", null);
        return new RestResponse(true, "Thành công!!!", apply.getStatus());
    }
}