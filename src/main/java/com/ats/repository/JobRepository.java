package com.ats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ats.entity.Job;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("SELECT distinct j FROM Job j " +
            "INNER JOIN j.skillneedforjobsById s " +
            "INNER JOIN s.skillBySkillId a " +
            "INNER JOIN a.skillmasterBySkillMasterId m " +
            "INNER JOIN j.cityByCityId c " +
            "INNER JOIN j.industryByIndustryId d " +
            "WHERE j.title LIKE CONCAT('%',LOWER(:search),'%') and " +
            "j.status = :status and " +
            "j.endDateForApply > :now and " +
            "c.id= :city and d.id= :industry " +
            "OR m.skillName LIKE CONCAT('%',LOWER(:search),'%')")
    Page<Job> searchJob(@Param("search") String search, Pageable pageable,
                        @Param("status") String status, @Param("now") Date endDateForApply,
                        @Param("city") int city, @Param("industry") int industry);

    @Query("Select b from Job b " +
            "where b.status = :status and " +
            "b.endDateForApply > :now " +
            "order by b.createdDate desc")
    Page<Job> getTop8(Pageable pageable, @Param("status") String status, @Param("now") Date endDateForApply);

    @Query("Select b from Job b where b.companyId = :companyId and b.id <> :jobId")
    List<Job> getJobByCompanyID(@Param("companyId") int companyId, @Param("jobId") int jobId);

    @Transactional
    @Modifying
    @Query("UPDATE Job b SET b.status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);

    Optional<Job> findById(int id);

    List<Job> findByUserId(int id);
}
