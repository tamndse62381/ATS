package com.ats.repository;

import com.ats.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.awt.print.Pageable;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer>{
//    @Query("SELECT i from Industry i where LOWER(i.name) LIKE CONCAT('%',LOWER(:name),'%')")
//    public List<Industry> findAndPaging(@Param("name") String name, Pageable pageable);
}
