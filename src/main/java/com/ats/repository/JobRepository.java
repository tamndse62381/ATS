package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ats.entity.Job;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
    @Query("Select b from Job b where b.title LIKE CONCAT('%',LOWER(:search),'%')")
    List<Job> searchJob(@Param("search") String search );

    @Query("Select b from Job b order by b.createdDate desc")
    List<Job> getTop8();
}
