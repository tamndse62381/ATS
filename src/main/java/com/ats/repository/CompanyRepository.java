package com.ats.repository;

import com.ats.entity.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("Select c from Company c where c.userid = :id")
     Company findComanyByEmployerID(@Param("id") int id);

    @Query("Select c from Company c")
     Page<Company> findAllPaging(Pageable pageable);
}
