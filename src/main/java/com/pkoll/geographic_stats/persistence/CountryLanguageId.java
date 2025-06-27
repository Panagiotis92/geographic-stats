package com.pkoll.geographic_stats.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryLanguageId implements Serializable {
    private static final long serialVersionUID = 3786687738503852376L;
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "language_id", nullable = false)
    private Integer languageId;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryLanguageId entity = (CountryLanguageId) o;
        return Objects.equals(this.languageId, entity.languageId) &&
                Objects.equals(this.countryId, entity.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, countryId);
    }

}