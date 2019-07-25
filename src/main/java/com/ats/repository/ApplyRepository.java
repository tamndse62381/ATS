package com.ats.repository;

import com.ats.entity.Apply;
import com.ats.entity.Cv;
import com.ats.entity.Job;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    @Query("Select a from Apply a where a.cvid  = :cvid and a.jobId = :jobid")
    Apply findForCheck(@Param("cvid") int cvid, @Param("jobid") int jobid);

    List<Apply> findAppliesByCvid(int Cvid);

    List<Apply> findAppliesByJobId(int JobId);
}
