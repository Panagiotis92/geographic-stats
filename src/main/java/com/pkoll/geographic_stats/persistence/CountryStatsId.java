package com.pkoll.geographic_stats.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryStatsId implements Serializable {
    private static final long serialVersionUID = -1848075818270523016L;
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "year", nullable = false)
    private Integer year;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryStatsId entity = (CountryStatsId) o;
        return Objects.equals(this.year, entity.year) &&
                Objects.equals(this.countryId, entity.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, countryId);
    }

}