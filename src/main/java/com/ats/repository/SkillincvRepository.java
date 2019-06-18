package com.ats.repository;

import com.ats.entity.Skillincv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillincvRepository extends JpaRepository<Skillincv, Integer> {
    @Query("Select s from Skillincv s where s.cvid = :id")
    public List<Skillincv> findListSkillmasterByCVID (@Param("id") int id);
}
