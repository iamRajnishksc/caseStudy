package com.example.CaseStudyBackend.controller;

import com.example.CaseStudyBackend.model.IPODetail;
import com.example.CaseStudyBackend.model.Sector;
import com.example.CaseStudyBackend.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sectors")
public class SectorController
{
    @Autowired
    private SectorRepository secrepo;

    @GetMapping(path = "")
    public ResponseEntity<List<Sector>> findAll() {
        return ResponseEntity.ok(secrepo.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Sector> findById(@PathVariable String id)

    {
        Optional<Sector> sectorDto = secrepo.findById(Long.valueOf(id));
//        if(sectorDto == null) {
//            throw new SectorNotFoundException("Sector not found for id : " + id);
//        }
        return ResponseEntity.ok(sectorDto.get());
    }

    @PostMapping(path = "")
    public ResponseEntity<Sector> save(@RequestBody Sector sectorDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(secrepo.save(sectorDto));
    }

    @PutMapping(path = "")
    public ResponseEntity<Sector> update(@RequestBody Sector sectorDto)
    {
        Sector updatedSectorDto = secrepo.save(sectorDto);
//        if(updatedSectorDto == null) {
//            throw new SectorNotFoundException("Sector not found for id : " + sectorDto.getId());
//        }
        return ResponseEntity.ok(updatedSectorDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id) {
        secrepo.deleteById(Long.valueOf(id));
    }

//    @GetMapping(path = "/{id}/companies")
//    public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String id)
//            throws SectorNotFoundException
//    {
//        List<CompanyDto> companyDtos = sectorService.getCompanies(id);
//        if(companyDtos == null) {
//            throw new SectorNotFoundException("Sector not found for id : " + id);
//        }
//        return ResponseEntity.ok(companyDtos);
//    }

    /* Feign Client Mapping */

//    @PostMapping(path = "/{sectorName}/companies")
//    public void addCompanyToSector(@PathVariable String sectorName, @RequestBody CompanyDto companyDto)
//            throws SectorNotFoundException
//    {
//        SectorDto sectorDto = sectorService.addCompanyToSector(sectorName, companyDto);
//        if(sectorDto == null) {
//            throw new SectorNotFoundException("Sector not found with name : " + sectorName);
//        }
//    }
}