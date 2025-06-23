package com.study.scrapperapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Scheduled(cron = "0 0 * * * ?")
    public void scheduleDatabaseDump() {
        try {
            databaseDump();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void databaseDump() throws Exception {
        String dbName = "jobsdb";
        String username = "postgres";
        String dumpFilePath = "jobsdb_dump.sql";

        String command = String.format(
                "pg_dump -U %s -h localhost -F c %s > %s",
                username, dbName, dumpFilePath);


        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        System.out.println("Database dump completed and saved to " + dumpFilePath);
    }
}

