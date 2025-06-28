package com.pkoll.geographic_stats.dto;

import java.math.BigDecimal;

public record CountryYearStatsDTO(String name, String countryCode3, BigDecimal gdp, Integer year, Integer population) {
}
