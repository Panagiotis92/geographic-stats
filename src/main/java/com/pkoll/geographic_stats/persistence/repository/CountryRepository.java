package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.persistence.Country;
import com.pkoll.geographic_stats.persistence.projection.CountrySummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    public List<CountrySummaryProjection> findAllBy();
}
