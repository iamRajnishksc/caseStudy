package com.example.CaseStudyBackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Sector {


    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;


    @Column(nullable = false)

    private String name;


    @Column(nullable = false)

    private String description;


    @OneToMany(mappedBy = "sector")

    @JsonIgnore

    private List<Company> companies = new ArrayList<>();




}