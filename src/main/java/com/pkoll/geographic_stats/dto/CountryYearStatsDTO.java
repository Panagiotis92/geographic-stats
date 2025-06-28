package com.pkoll.geographic_stats.dto;

import java.math.BigDecimal;

public record CountryYearStatsDTO(String name, String countryCode3, BigDecimal gdp, Integer year, Integer population) {
    public double calculateGdpPopulationRation() {
        if (gdp == null || population == null || population == 0) return 0;
        return gdp.doubleValue() / population;
    }
}
