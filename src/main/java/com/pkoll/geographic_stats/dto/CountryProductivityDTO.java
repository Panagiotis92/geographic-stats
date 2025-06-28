package com.pkoll.geographic_stats.dto;

import com.pkoll.geographic_stats.persistence.CountryStat;

import java.math.BigDecimal;

public record CountryProductivityDTO(String name, String countryCode3, BigDecimal gdp, Integer year, Integer population) {
}
