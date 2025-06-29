package com.pkoll.geographic_stats.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record CountrySummaryDTO(
        @Schema(example = "Greece") String name,
        @Schema(example = "131626.00") BigDecimal area,
        @Schema(example = "GR") String countryCode2) {
}
