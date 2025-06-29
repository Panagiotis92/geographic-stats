package com.pkoll.geographic_stats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CountyYearStatsSearchRequestDTO(String region, Integer yearFrom, Integer yearTo,
                                              @NotNull @Min(1) Integer pageNumber, @NotNull @Min(5) Integer pageSize) {
}
