package com.ats.repository;

import com.ats.entity.Servicepackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicepackageRepository extends JpaRepository<Servicepackage,Integer> {
}