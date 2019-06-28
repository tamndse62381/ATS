package com.ats.repository;

import com.ats.entity.Employercompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployercompanyRepository extends JpaRepository<Employercompany,Integer> {
}
