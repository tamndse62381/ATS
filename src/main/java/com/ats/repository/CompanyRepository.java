package com.ats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ats.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	@Query("SELECT c from Company c where LOWER(c.nameCompany) LIKE CONCAT('%',LOWER(:namecompany),'%')")
    public List<Company> findByName(@Param("namecompany") String namecompany);

}
