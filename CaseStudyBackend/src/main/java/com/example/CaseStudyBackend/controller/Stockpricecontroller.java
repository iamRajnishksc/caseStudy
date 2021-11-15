package com.example.CaseStudyBackend.controller;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.CompanyCompareRequest;
import com.example.CaseStudyBackend.model.StockPrice;
import com.example.CaseStudyBackend.model.Stockrequest;
import com.example.CaseStudyBackend.repository.Companyrespository;
import com.example.CaseStudyBackend.repository.Stockpricerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/stock-price-service/stockPrices")
public class Stockpricecontroller {
    @Autowired
    private Stockpricerepository sprepo;

    @Autowired
    private Companyrespository cmprepo;

    @Autowired
    private Stockpricerepository stkrepo;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "")
    public ResponseEntity<List<StockPrice>> findAll() {
        return ResponseEntity.ok(sprepo.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StockPrice> findById(@PathVariable String id)
    {
        Optional<StockPrice> stockPriceDto = sprepo.findById(Long.valueOf(id));

        return ResponseEntity.ok(stockPriceDto.get());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/compareCompany")
    public ResponseEntity<?> companyComparison(@RequestBody CompanyCompareRequest compareRequest)
    {

        Company comp = cmprepo.findByName(compareRequest.getName());
        List<StockPrice>  stockPrices = stkrepo.findByCompanyCodeAndStockExchangeName(comp.getCode(),compareRequest.getStockExchangeName());


        List<StockPrice> filteredList = stockPrices.stream()
                .filter(stockPrice -> {
                    Date date = null;
                    date = stockPrice.getDate();
                    Date fromDate = null, toDate = null;
                    try {
                        fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getFromPeriod());
                        toDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getToPeriod());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    return date.after(fromDate) && date.before(toDate);
                })
                .collect(Collectors.toList());



        return ResponseEntity.ok(filteredList);
    }

//    @GetMapping(path = "/compareSector")
//    @HystrixCommand(fallbackMethod = "defaultResponse")
//    public ResponseEntity<?> sectorComparison(@RequestBody SectorCompareRequest compareRequest)
//    {
//        List<StockPriceDto> stockPriceDtos = null;
//        try {
//            stockPriceDtos = stockPriceService.getStockPricesForSectorComparison(compareRequest);
//        } catch (ParseException e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid Date Format");
//        }
//        return ResponseEntity.ok(stockPriceDtos);
//    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "")
    public ResponseEntity<?> save(@RequestBody List<Stockrequest> stockPriceDtos) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateAsString = "25/12/2010";


        for(int  i=0;i<stockPriceDtos.size();i++){
            Stockrequest s = stockPriceDtos.get(i);
            Date date = sourceFormat.parse(s.getDate());
            StockPrice stockPrice =new StockPrice();
            stockPrice.setStockExchangeName(s.getStockExchangeName());
            stockPrice.setCompanyCode(s.getCompanyCode());
            stockPrice.setDate(date);
            stockPrice.setTime(s.getTime());
            stockPrice.setPrice(s.getPrice());
            stockPrice.setCompany(cmprepo.findByCode(s.getCompanyCode()));
            sprepo.save(stockPrice);
        }


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(1);

    }

//    @PutMapping(path = "")
//    public ResponseEntity<StockPrice> update(@RequestBody StockPrice stockPriceDto)
//    {
//        StockPrice updatedStockPriceDto = stockPriceService.update(stockPriceDto);
//        if(updatedStockPriceDto == null) {
//            throw new StockPriceNotFoundException("Stock Price not found with id : " + stockPriceDto.getId());
//        }
//        return ResponseEntity.ok(updatedStockPriceDto);
//    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id) {
        sprepo.deleteById(Long.valueOf(id));
    }

    /* Feign Client Default Response */

//    public ResponseEntity<?> defaultResponse(@RequestBody SectorCompareRequest compareRequest) {
//        String err = "Fallback error as the microservice is down.";
//        return ResponseEntity
//                .status(HttpStatus.SERVICE_UNAVAILABLE)
//                .body(err);
//    }
}
