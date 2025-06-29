package com.pkoll.geographic_stats.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CountryYearStatsSearchResponsePage(List<CountryYearStatsSearchResponseDTO> results, @Schema(example = "50") long totalAmount) {
}
