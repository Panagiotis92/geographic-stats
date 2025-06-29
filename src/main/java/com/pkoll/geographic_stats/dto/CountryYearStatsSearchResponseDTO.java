package com.pkoll.geographic_stats.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record CountryYearStatsSearchResponseDTO(
        @Schema(example = "Greece") String name,
        @Schema(example = "218032000000") BigDecimal gdp,
        @Schema(example = "2018") Integer year,
        @Schema(example = "10727668") Integer population,
        @Schema(example = "Southern Europe") String region,
        @Schema(example = "Europe") String continent) {
}