package com.ats.repository;

import com.ats.entity.Skillneedforjob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillNeedForJobRepository extends JpaRepository<Skillneedforjob, Integer> {
}
