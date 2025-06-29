package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.persistence.CountryStats;
import com.pkoll.geographic_stats.persistence.CountryStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryStatsRepository extends JpaRepository<CountryStats, CountryStatsId> {
    @Query("SELECT cs.country.name, cs.country.countryCode3, cs.gdp, cs.id.year, cs.population from CountryStats cs")
    public List<CountryYearStatsDTO> selectCountryYearStats();
}
