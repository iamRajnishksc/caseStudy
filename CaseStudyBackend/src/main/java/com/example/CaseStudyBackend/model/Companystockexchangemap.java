package com.example.CaseStudyBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "CompanyStockexchangemap")
public class Companystockexchangemap {


    @Id
    @GeneratedValue
    private long id;

    private String CompanyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stockexchange stockexchange;


}

