package com.ats.repository;

import com.ats.entity.Socialactivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialactivityRepository extends JpaRepository<Socialactivity, Integer> {
    @Query("Select s from Socialactivity s where s.cv.id = :id")
    public List<Socialactivity> findAllSocialactivityByCVID (@Param("id") int id);
}
