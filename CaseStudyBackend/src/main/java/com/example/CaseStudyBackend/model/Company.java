package com.example.CaseStudyBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String companyName;


    @Column(nullable = false)
    private Double turnover;


    @Column(nullable = false)
    private String ceo;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String stockExchangeNames;

    @Column(nullable = false)
    @Type(type = "text")
    private String boardOfDirectors;


    @Column(nullable = false)
    @Type(type = "text")
    private String companyBrief;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "company", cascade = {CascadeType.ALL})
    @JsonIgnore
    private IPODetail ipo;


    @OneToMany(targetEntity = Companystockexchangemap.class)

    private List<Companystockexchangemap> compstockmap;




    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Sector sector;
    private String sectorname;
    //this will be used to pass sectorname params
    //  To be passed to the controller from UI or endpoint as part of //requestbody ,based on which a derived query on sector will fetch the sector //and update the relatiohship field






}