package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.entity.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer>{

}
