package com.pkoll.geographic_stats.dto;

import java.util.List;

public record CountryYearStatsSearchResponsePage(List<CountryYearStatsSearchResponseDTO> results, long totalAmount) {
}
