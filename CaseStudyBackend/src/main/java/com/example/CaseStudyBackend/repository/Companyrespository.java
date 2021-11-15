package com.example.CaseStudyBackend.repository;

import com.example.CaseStudyBackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface Companyrespository  extends JpaRepository<Company, Long> {


    @Query(value = "Select * FROM company WHERE company_name = ?1",nativeQuery = true)
   Company findByName(String name);

    @Query(value = "Select * FROM company WHERE code = ?1",nativeQuery = true)
    Company findByCode(String code);

}