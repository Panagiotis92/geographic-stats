package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.dto.CountryProductivityDTO;
import com.pkoll.geographic_stats.persistence.CountryStat;
import com.pkoll.geographic_stats.persistence.CountryStatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryStatsRepository extends JpaRepository<CountryStat, CountryStatId> {
    @Query("SELECT cs.country.name, cs.country.countryCode3, cs.gdp, cs.id.year, cs.population from CountryStat cs")
    public List<CountryProductivityDTO> selectCountryYearStats();
}
