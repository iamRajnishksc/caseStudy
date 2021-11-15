package com.example.CaseStudyBackend.repository;

import javax.persistence.Query;

import com.example.CaseStudyBackend.model.Companystockexchangemap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface CompanyStockexchangemaprepo extends JpaRepository<Companystockexchangemap, Long> {





}
