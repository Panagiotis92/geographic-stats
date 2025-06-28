package com.pkoll.geographic_stats.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "country_stats")
public class CountryStat {
    @EmbeddedId
    private CountryStatId id;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "population")
    private Integer population;

    @Column(name = "gdp", precision = 15)
    private BigDecimal gdp;

    public CountryStatId getId() {
        return id;
    }

    public void setId(CountryStatId id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getGdp() {
        return gdp;
    }

    public void setGdp(BigDecimal gdp) {
        this.gdp = gdp;
    }

    public double getGdpPopulationRation() {
        if (gdp == null || population == null || population == 0) return 0;
        return gdp.doubleValue() / population;
    }
}