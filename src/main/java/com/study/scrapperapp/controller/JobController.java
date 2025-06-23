package com.study.scrapperapp.controller;

import com.study.scrapperapp.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @Autowired
    private ScrapingService scrapingService;

    @GetMapping("/scrape")
    public ResponseEntity<String> scrapeJobs(@RequestParam("jobFunction") String jobFunction) {
        scrapingService.scrapeJobs(jobFunction);
        return ResponseEntity.ok("Scraping started for: " + jobFunction);
    }
}

