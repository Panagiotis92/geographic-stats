package com.pkoll.geographic_stats.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "region_areas")
public class RegionArea {
    @Id
    @Column(name = "region_name", nullable = false, length = 100)
    private String regionName;

    @Column(name = "region_area", nullable = false, precision = 15, scale = 2)
    private BigDecimal regionArea;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigDecimal getRegionArea() {
        return regionArea;
    }

    public void setRegionArea(BigDecimal regionArea) {
        this.regionArea = regionArea;
    }

}