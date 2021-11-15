package com.example.CaseStudyBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
@Table(name = "StockPrice")
public class StockPrice {

    @Id
    @GeneratedValue
    private long id;
    private String stockExchangeName;
    private String companyCode;
    private LocalDateTime localdatetime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    private Date date;
    private String time;
    private float price;


}
