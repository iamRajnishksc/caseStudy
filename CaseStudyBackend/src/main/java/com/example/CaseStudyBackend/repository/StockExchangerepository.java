package com.example.CaseStudyBackend.repository;

import com.example.CaseStudyBackend.model.Sector;
import com.example.CaseStudyBackend.model.Stockexchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface StockExchangerepository extends JpaRepository<Stockexchange, Long> {

    @Query(value = "Select * FROM stock_exchange WHERE name = ?1",nativeQuery = true)
    Stockexchange findByName(String name);

}
