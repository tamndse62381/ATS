package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

}
