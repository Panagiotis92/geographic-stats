package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.persistence.CountryLanguage;
import com.pkoll.geographic_stats.persistence.CountryLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {
    @Query("SELECT cl.language.language FROM CountryLanguage cl WHERE cl.country.countryCode2 = ?1")
    public List<String> selectLanguagesByCountryCode2(String countryCode2);
}
