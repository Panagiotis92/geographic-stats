package com.pkoll.geographic_stats.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CountyYearStatsSearchRequestDTO(
        @Schema(example = "Southern Europe") String region,
        @Schema(example = "1990") Integer yearFrom,
        @Schema(example = "2000") Integer yearTo,
        @Schema(example = "1") @Min(value = 1,message = "Should be positive integer") int pageNumber,
        @Schema(example = "5") @Min(value = 5, message = "Should be greater or equal to 5") int pageSize) {
}
