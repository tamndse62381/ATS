package com.ats.repository;

import com.ats.entity.Employercompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployercompanyRepository extends JpaRepository<Employercompany,Integer> {
    @Query("Select b from Employercompany b where b.userId = :userId")
    Employercompany findCompanyByUserId(@Param("userId") int userId);
}
