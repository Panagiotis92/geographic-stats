package com.pkoll.geographic_stats.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record CountryYearStatsDTO(
        @Schema(example = "Greece") String name,
        @Schema(example = "GRC") String countryCode3,
        @Schema(example = "218032000000") BigDecimal gdp,
        @Schema(example = "2018") Integer year,
        @Schema(example = "10727668") Integer population) {
    public double calculateGdpPopulationRation() {
        if (gdp == null || population == null || population == 0) return 0;
        return gdp.doubleValue() / population;
    }
}
