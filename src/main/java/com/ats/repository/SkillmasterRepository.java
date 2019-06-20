package com.ats.repository;

import com.ats.entity.Skillmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillmasterRepository extends JpaRepository<Skillmaster, Integer>{
    @Query("Select s from Skillmaster s where s.skillTypeId = :id")
    public List<Skillmaster> findAllLanguageSkill (@Param("id") int id);
}
