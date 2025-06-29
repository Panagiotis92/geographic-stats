package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.dto.CountryYearStatsSearchResponseDTO;
import com.pkoll.geographic_stats.dto.CountyYearStatsSearchRequestDTO;
import com.pkoll.geographic_stats.persistence.QContinent;
import com.pkoll.geographic_stats.persistence.QCountry;
import com.pkoll.geographic_stats.persistence.QCountryStats;
import com.pkoll.geographic_stats.persistence.QRegion;
import com.pkoll.geographic_stats.persistence.repository.CountryLanguageRepository;
import com.pkoll.geographic_stats.persistence.repository.CountryRepository;
import com.pkoll.geographic_stats.persistence.repository.CountryStatsRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private CountryLanguageRepository countryLanguageRepository;
    private CountryStatsRepository countryStatsRepository;
    private EntityManager entityManager;

    public CountryService(CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository,
                          CountryStatsRepository countryStatsRepository, EntityManager entityManager) {
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryStatsRepository = countryStatsRepository;
        this.entityManager = entityManager;
    }

    public List<CountrySummaryDTO> getAll() {
        return countryRepository.selectSummaryOrderByNameAsc();
    }

    public List<String> getLanguages(String countryCode2) {
        return countryLanguageRepository.selectLanguagesByCountryCode2(countryCode2);
    }

    public Collection<CountryYearStatsDTO> findMostProductiveYears() {
        return countryStatsRepository.selectCountryYearStats()
                .stream()
                .collect(Collectors.groupingBy(
                        CountryYearStatsDTO::countryCode3,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(CountryYearStatsDTO::calculateGdpPopulationRation)),
                                Optional::get)))
                .values();
    }

    public List<CountryYearStatsSearchResponseDTO> search(CountyYearStatsSearchRequestDTO requestDTO) {
        QCountryStats countryStats = QCountryStats.countryStats;
        QCountry country = QCountry.country;
        QRegion region = QRegion.region;
        QContinent continent = QContinent.continent;

        BooleanBuilder predicate = new BooleanBuilder();
        if (requestDTO.region() != null) {
            predicate.and(region.name.eq(requestDTO.region()));
        }
        if (requestDTO.yearFrom() != null) {
            predicate.and(countryStats.id.year.goe(requestDTO.yearFrom()));
        }
        if (requestDTO.yearTo() != null) {
            predicate.and(countryStats.id.year.loe(requestDTO.yearTo()));
        }

        return new JPAQueryFactory(entityManager)
                .select(Projections.constructor(CountryYearStatsSearchResponseDTO.class,
                                country.name, country.countryCode3,
                                countryStats.gdp, countryStats.id.year, countryStats.population,
                                region.name, continent.name))
                .from(countryStats)
                .join(countryStats.country, country)
                .join(country.region, region)
                .join(region.continent, continent)
                .where(predicate)
                .fetch();
    }
}
