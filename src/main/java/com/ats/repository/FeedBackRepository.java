package com.ats.repository;

import com.ats.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedBackRepository extends JpaRepository<Feedback,Integer> {
    @Query("select f from Feedback f order by f.createdDate desc")
    Page<Feedback> findAllByCreatedDate(Pageable pageable);
}
