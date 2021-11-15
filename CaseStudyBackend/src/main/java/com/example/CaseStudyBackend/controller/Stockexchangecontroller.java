package com.example.CaseStudyBackend.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.CaseStudyBackend.model.Stockexchange;
import com.example.CaseStudyBackend.repository.StockExchangerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/stockExchanges")
@RestController
public class  Stockexchangecontroller {
    @Autowired
    StockExchangerepository stkrep;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "")
    public ResponseEntity<List<Stockexchange>> getStockExchangesList() {
        return ResponseEntity.ok(stkrep.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Stockexchange> getStockExchangeDetails(@PathVariable String id)

    {
        Optional<Stockexchange> stockExchangeDto = stkrep.findById(Long.valueOf(id));

        return ResponseEntity.ok(stockExchangeDto.get());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "")
    public ResponseEntity<Stockexchange> addStockExchange(@RequestBody Stockexchange stockExchangeDto) {
        return ResponseEntity.ok(stkrep.save(stockExchangeDto));
    }

//    @PutMapping(path = "")
//    public ResponseEntity<Stockexchange> editStockExchange(@RequestBody Stockexchange stockExchangeDto)
//
//    {
//        Stockexchange updatedStockExchangeDto = stockExchangeService.editStockExchange(stockExchangeDto);
//        if(updatedStockExchangeDto == null) {
//            throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + stockExchangeDto.getId());
//        }
//        return ResponseEntity.ok(updatedStockExchangeDto);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/{id}")
    public void deleteStockExchange(@PathVariable String id) {
        stkrep.deleteById(Long.valueOf(id));
    }

//    @GetMapping(path = "/{id}/companies")
//    public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String id)
//            throws StockExchangeNotFoundException
//    {
//        List<CompanyDto> companyDtos = stkrep.getCompanies(id);
//        if(companyDtos == null) {
//            throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
//        }
//        return ResponseEntity.ok(companyDtos);
//    }
//
//    /* Feign Client Mapping */
//
//    @PostMapping(path = "/{stockExchangeName}/companies")
//    public void addCompanyToStockExchange(@PathVariable String stockExchangeName, @RequestBody CompanyDto companyDto)
//            throws StockExchangeNotFoundException
//    {
//        Stockexchange stockExchangeDto = stkrep.addCompanyToStockExchange(stockExchangeName, companyDto);
//        if(stockExchangeDto == null) {
//            throw new StockExchangeNotFoundException("Stock Exchange Not Found with name : " + stockExchangeName);
//        }
//    }

}