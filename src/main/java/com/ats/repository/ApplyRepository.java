package com.ats.repository;

import com.ats.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    @Query("Select a from Apply a where a.jobseekerid  = :jobseekerid and a.jobid = :jobid")
    Apply findApplyBy (@Param("jobseekerid") int jobseekerid, @Param("jobid") int jobid);
}
