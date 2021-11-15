package com.example.CaseStudyBackend.repository;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.IPODetail;
import com.example.CaseStudyBackend.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query(value = "Select * FROM sector WHERE name = ?1",nativeQuery = true)
    Sector findByName(String name);
}