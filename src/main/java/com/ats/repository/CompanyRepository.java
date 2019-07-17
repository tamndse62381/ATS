package com.ats.repository;

import com.ats.dto.CompanyDTO;
import com.ats.entity.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("Select c from Company c")
     Page<Company> findAllPaging(Pageable pageable);

    Company findCompanyByNameCompany(String nameCompany);

    Optional<Company> findCompanyById(Integer id);

    Company findByEmail(String email);

    Company findByNameCompany(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.status = :newStatus WHERE c.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);
}
