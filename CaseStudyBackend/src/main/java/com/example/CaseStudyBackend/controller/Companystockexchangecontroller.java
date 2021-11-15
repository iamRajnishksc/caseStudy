package com.example.CaseStudyBackend.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.Stockexchange;
import com.example.CaseStudyBackend.repository.CompanyStockexchangemaprepo;
import com.example.CaseStudyBackend.repository.Companyrespository;
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

@RestController
public class Companystockexchangecontroller {
    @Autowired
    CompanyStockexchangemaprepo cstkmaprep;

    @Autowired
    Companyrespository cmprep;

    @Autowired
    StockExchangerepository stkrep;
    @RequestMapping(value="/saveexchangemap/{exchangename}{companyname}", method=RequestMethod.POST)
    public String welcomepage(@PathVariable String exchangename ,@PathVariable String exchangenamecompanyname) {
        Stockexchange stk = new Stockexchange();


        return "Welcome to "+exchangename;
    }


//	@PostMapping("/insertcompany/{id}{Name}")
//	public ResponseEntity<Object> createPost(@PathVariable int id, @PathVariable String Name,@RequestBody Company newcomp) {
    //       newcomp = new Company((long)id,Name);
    //Companyrepository.save(newcomp);

    //	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newcomp.getId())
    //	.toUri();

    //	return ResponseEntity.created(location).build();

    //}
    //return new ResponseEntity<String>("Hello, you!", HttpStatus.OK);

//	@GetMapping("/Company/{id}")
//	Optional<Company> one(@PathVariable Long id) {
    //   Company c = new Company(333,"post");
    //   Companyrepository.save(c);

    //	return(c.getId());

    //}





}