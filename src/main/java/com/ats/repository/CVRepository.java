package com.ats.repository;

import com.ats.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<Cv, Integer> {
    @Query("Select c from Cv c where c.email = :email")
    public Cv findCVByCVID (@Param("email") String email);

    Optional<Cv> findById(int id);

    List<Cv> findByUserid(int id);
}
