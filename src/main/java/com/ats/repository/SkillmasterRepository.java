package com.ats.repository;

import com.ats.entity.Skillmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillmasterRepository extends JpaRepository<Skillmaster, Integer>{
}
