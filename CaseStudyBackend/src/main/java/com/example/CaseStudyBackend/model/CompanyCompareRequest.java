package com.example.CaseStudyBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanyCompareRequest
{
    private String name;
    private String stockExchangeName;
    private String fromPeriod;
    private String toPeriod;
    private String periodicity;

}