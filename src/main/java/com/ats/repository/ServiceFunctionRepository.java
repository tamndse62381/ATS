package com.ats.repository;

import com.ats.entity.Servicefunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFunctionRepository extends JpaRepository<Servicefunction,Integer> {
}
