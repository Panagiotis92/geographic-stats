package com.pkoll.geographic_stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GeographicStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeographicStatsApplication.class, args);
    }

}
