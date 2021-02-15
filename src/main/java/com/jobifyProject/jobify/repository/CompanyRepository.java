package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
}

