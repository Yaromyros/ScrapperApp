package com.study.scrapperapp.service;

import com.study.scrapperapp.repository.JobRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import com.study.scrapperapp.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class ScrapingService {

    @Autowired
    private JobRepository jobRepository;

    public void scrapeJobs(String jobFunction) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jobs.techstars.com/jobs?function=" + jobFunction);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource);

        Elements jobElements = doc.select(".job-item");

        for (Element jobElement : jobElements) {
            String title = jobElement.select(".job-title").text();
            String company = jobElement.select(".company-name").text();
            String location = jobElement.select(".job-location").text();
            String description = jobElement.select(".job-description").text();
            String jobUrl = jobElement.select("a").attr("href");
            String companyUrl = jobElement.select(".company-name a").attr("href");
            String logoUrl = jobElement.select(".company-logo img").attr("src");
            String laborFunction = jobFunction;
            String tags = jobElement.select(".job-tags").text();
            long postedDate = System.currentTimeMillis();

            Job job = new Job(title, company, location, description, jobUrl, companyUrl, logoUrl, laborFunction, postedDate, tags);

            jobRepository.save(job);

            System.out.println("Data saved: " + job);
        }

        driver.quit();
    }
}

