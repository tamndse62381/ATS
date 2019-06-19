package com.ats.repository;

import com.ats.dto.CitySummary;
import com.ats.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("Select c from City c where c.id = :id")
    CitySummary getCityNameById(@Param("id") int id);

    List<City> findAllBy();
}
