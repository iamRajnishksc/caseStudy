package com.example.CaseStudyBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StockExchange")
public class Stockexchange {

    @Id
    @GeneratedValue
    private long id;
    private String name;


    @OneToMany(targetEntity = Companystockexchangemap.class)
    private List<Companystockexchangemap> compstockmap;

    @Column(nullable = false)
    @Type(type = "text")
    private String description;

    @Column(nullable = false)
    @Type(type = "text")
    private String address;

    @Column(nullable = false)
    @Type(type = "text")
    private String remarks;





}
