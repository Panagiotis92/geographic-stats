package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.persistence.CountryStat;
import com.pkoll.geographic_stats.persistence.CountryStatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryStatsRepository extends JpaRepository<CountryStat, CountryStatId> {
}
