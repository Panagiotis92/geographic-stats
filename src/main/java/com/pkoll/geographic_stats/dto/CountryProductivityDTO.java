package com.pkoll.geographic_stats.dto;

import com.pkoll.geographic_stats.persistence.CountryStat;

import java.math.BigDecimal;

public record CountryProductivityDTO(String name, String countryCode3, BigDecimal gdp, Integer year, Integer population) {
    public CountryProductivityDTO(CountryStat countryStat) {
        this(countryStat.getCountry().getName(), countryStat.getCountry().getCountryCode3(), countryStat.getGdp(), countryStat.getId().getYear(), countryStat.getPopulation());
    }
}
