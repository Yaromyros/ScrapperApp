package com.study.scrapperapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String description;
    private String jobUrl;
    private String companyUrl;
    private String logoUrl;
    private String laborFunction;
    private Long postedDate;
    private String tags;

    public Job(String title, String company, String location, String description, String jobUrl,
               String companyUrl, String logoUrl, String laborFunction, Long postedDate, String tags) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.jobUrl = jobUrl;
        this.companyUrl = companyUrl;
        this.logoUrl = logoUrl;
        this.laborFunction = laborFunction;
        this.postedDate = postedDate;
        this.tags = tags;
    }
}


