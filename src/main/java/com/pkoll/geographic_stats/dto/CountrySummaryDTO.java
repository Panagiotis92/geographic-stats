package com.pkoll.geographic_stats.dto;

import java.math.BigDecimal;

public record CountrySummaryDTO(String name, BigDecimal area, String countryCode2) {
}
