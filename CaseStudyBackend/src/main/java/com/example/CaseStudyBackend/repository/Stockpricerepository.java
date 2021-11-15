package com.example.CaseStudyBackend.repository;

import com.example.CaseStudyBackend.model.Sector;
import com.example.CaseStudyBackend.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface Stockpricerepository extends JpaRepository<StockPrice,Long>{


    @Query(value = "Select * FROM stock_price WHERE company_code = ?1 and stock_exchange_name = ?2",nativeQuery = true)

    List<StockPrice> findByCompanyCodeAndStockExchangeName(String company_code, String stock_exchange_name);


}
