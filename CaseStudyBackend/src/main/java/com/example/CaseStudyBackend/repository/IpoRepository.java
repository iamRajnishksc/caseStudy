package com.example.CaseStudyBackend.repository;


import com.example.CaseStudyBackend.model.IPODetail;
import com.example.CaseStudyBackend.model.Stockexchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface IpoRepository extends JpaRepository<IPODetail, Long> {


}