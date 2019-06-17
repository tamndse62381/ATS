package com.ats.repository;

import com.ats.entity.Skillincv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillincvRepository extends JpaRepository<Skillincv, Integer> {
}
