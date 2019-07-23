package com.ats.repository;

import com.ats.entity.Functionpackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionPackageRepository extends JpaRepository<Functionpackage ,Integer> {
}
