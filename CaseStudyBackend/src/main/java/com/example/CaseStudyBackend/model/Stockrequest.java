package com.example.CaseStudyBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Stockrequest {
    @Id
    @GeneratedValue
    private long id;
    private String stockExchangeName;
    private String companyCode;

    private String date;
    private String time;
    private float price;
}
