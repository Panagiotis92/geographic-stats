package com.pkoll.geographic_stats.persistence.projection;

import java.math.BigDecimal;

public interface CountrySummaryProjection {
    public String getName();
    public BigDecimal getArea();
    public String getCountryCode2();
}
