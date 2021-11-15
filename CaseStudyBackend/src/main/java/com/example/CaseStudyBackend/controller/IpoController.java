package com.example.CaseStudyBackend.controller;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.IPODetail;
import com.example.CaseStudyBackend.repository.Companyrespository;
import com.example.CaseStudyBackend.repository.IpoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/ipos")
public class IpoController {

    @Autowired
    private IpoRepository ipoRepository;

    @Autowired
    private Companyrespository cmprep;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "")
    public ResponseEntity<List<IPODetail>> findAll() {
        return ResponseEntity.ok(ipoRepository.findAll());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<IPODetail> findById(@PathVariable String id)
    {
        Optional<IPODetail> ipodetails = ipoRepository.findById(Long.valueOf(id));
//
        return ResponseEntity.ok(ipodetails.get());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "")
    public ResponseEntity<IPODetail> save(@RequestBody IPODetail ipo)
        {
        Company company = cmprep.findByName(ipo.getCompanyName());
        if(company== null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        }
        ipo.setCompany(company);
        ipoRepository.save(ipo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ipo);
    }


//    @PutMapping(path = "")
//    public ResponseEntity<IPODetail> update(@RequestBody IPODetail ipo)
//
//    {
//        IPODetail ipodetails = ipoRepository.update(ipoDto);
//
//        return ResponseEntity.ok(updatedIpoDto);
//    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id) {
        ipoRepository.deleteById(Long.valueOf(id));
    }
}
