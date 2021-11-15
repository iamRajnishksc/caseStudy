package com.example.CaseStudyBackend.controller;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.Sector;
import com.example.CaseStudyBackend.model.Stockexchange;
import com.example.CaseStudyBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/company-service/companies")
public class CompanyController {

    @Autowired
    Companyrespository cmprep;

    @Autowired
    private SectorRepository secrepo;

    @Autowired
    private StockExchangerepository stkexcrepo;

    @Autowired
    public Companystockexchangemaprepo2 cserepo;




    @GetMapping(path = "")
    public ResponseEntity<List<Company>> getCompanies()
    {
        return ResponseEntity
                .ok(cmprep.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompanyDetails(@PathVariable String id)

    {
        Optional<Company> companyDto = cmprep.findById(Long.valueOf(id));

        return ResponseEntity.ok(companyDto.get());
    }

//    @GetMapping(path = "/match/{pattern}")
//    public ResponseEntity<List<Company>> getMatchingCompanies(@PathVariable String pattern)
//    {
//        return ResponseEntity.ok(companyService.getMatchingCompanies(pattern));
//    }

//    @GetMapping(path = "/{id}/ipos")
//    public ResponseEntity<List<IpoDto>> getCompanyIpoDetails(@PathVariable String id)
//    {
//        List<IpoDto> ipoDtos = companyService.getCompanyIpoDetails(id);
//        if(ipoDtos == null) {
//            throw new CompanyNotFoundException("Company not found at id : " + id);
//        }
//        return ResponseEntity.ok(ipoDtos);
//    }

//    @GetMapping(path = "/{id}/stockPrices")
//    public ResponseEntity<List<StockPriceDto>> getStockPrices(@PathVariable String id)
//            throws CompanyNotFoundException
//    {
//        List<StockPriceDto> stockPriceDtos = companyService.getStockPrices(id);
//        if(stockPriceDtos == null) {
//            throw new CompanyNotFoundException("Company not found at id : " + id);
//        }
//        return ResponseEntity.ok(stockPriceDtos);
//    }

    @PostMapping(path = "")
    public ResponseEntity<?> addCompany(@RequestBody Company company) {
        Sector sector =  secrepo.findByName(company.getSectorname());


        company.setSector(sector);
        cmprep.save(company);

        String[] stockExchangeNames = company.getStockExchangeNames().split(",");
        for(String temp: stockExchangeNames) {
            cserepo.retlist(company.getCompanyName(),temp,company.getCode());
        }




        return  ResponseEntity.ok(company);

    }

    @PutMapping(path = "")
    public ResponseEntity<Company> editCompany(@RequestBody Company companyDto)
    {
        Company updatedCompanyDto = cmprep.save(companyDto);

        return ResponseEntity.ok(updatedCompanyDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable String id) {
        cmprep.deleteById(Long.valueOf(id));
    }

    /* Feign Client Mappings */

//    @PostMapping(path = "/{companyName}/ipos")
//    public void addIpoToCompany(@PathVariable String companyName, @RequestBody IpoDto ipoDto)
//            throws CompanyNotFoundException
//    {
//        CompanyDto companyDto = companyService.addIpoToCompany(companyName, ipoDto);
//        if(companyDto == null) {
//            throw new CompanyNotFoundException("Company not with name : " + companyName);
//        }
//    }
//
//    @PostMapping(path = "/{companyCode}/stockPrices")
//    public void addStockPriceToCompany(@PathVariable String companyCode, @RequestBody StockPriceDto stockPriceDto)
//            throws CompanyNotFoundException
//    {
//        CompanyDto companyDto = companyService.addStockPriceToCompany(companyCode, stockPriceDto);
//        if(companyDto == null) {
//            throw new CompanyNotFoundException("Company not with code : " + companyCode);
//        }
//    }

    /* Feign Client Default Response */

    public ResponseEntity<?> defaultResponse(@RequestBody Company companyDto) {
        String err = "Fallback error as the microservice is down.";
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(err);
    }
}
