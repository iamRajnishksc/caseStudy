package com.example.CaseStudyBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IPODetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Double pricePerShare;

    @Column(nullable = false)
    private Long totalNumberOfShares;

    private LocalDateTime openDateTime;

    @Column(nullable = false)
    @Type(type = "text")
    private String remarks;


    @ManyToMany
    private List<Stockexchange> stockExchanges = new ArrayList<>();


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "company_id", insertable = true)
    private Company company;




}