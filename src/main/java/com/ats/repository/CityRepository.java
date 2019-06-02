package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
