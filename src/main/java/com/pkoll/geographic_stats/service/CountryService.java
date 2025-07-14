package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.*;
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
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
        return countryRepository.selectSummary();
    }

    public List<String> getLanguages(String countryCode2) {
        return countryLanguageRepository.selectLanguagesByCountryCode2(countryCode2);
    }

    /**
     * Retrieves year stats from database.
     * Groups them by country.
     * For each group selects the year stats with maximum gdp per population ratio.
     *
     * @return {@link List<CountryYearStatsDTO>}
     */
    @Cacheable("mostProductiveYear")
    public List<CountryYearStatsDTO> findMostProductiveYear() {
        return countryStatsRepository.selectAll()
                .stream()
                .collect(Collectors.groupingBy(
                        CountryYearStatsDTO::countryCode3,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(CountryYearStatsDTO::calculateGdpPopulationRation)),
                                Optional::get)))
                .values()
                .stream()
                .sorted(Comparator.comparing(CountryYearStatsDTO::name))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates and executes queries for year stats dynamic search.
     * Returns page results and total amount of results.
     *
     * @param requestDTO {@link CountyYearStatsSearchRequestDTO}
     * @return {@link CountryYearStatsSearchResponsePage}
     */
    public CountryYearStatsSearchResponsePage searchYearStats(CountyYearStatsSearchRequestDTO requestDTO) {
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

        List<CountryYearStatsSearchResponseDTO> results = new JPAQueryFactory(entityManager)
                .select(Projections.constructor(CountryYearStatsSearchResponseDTO.class,
                        country.name, countryStats.gdp,
                        countryStats.id.year, countryStats.population,
                        region.name, continent.name))
                .from(countryStats)
                .join(countryStats.country, country)
                .join(country.region, region)
                .join(region.continent, continent)
                .where(predicate)
                .orderBy(country.name.asc(), countryStats.id.year.asc())
                .offset((requestDTO.pageNumber() - 1) * requestDTO.pageSize())
                .limit(requestDTO.pageSize())
                .fetch();

        long totalAmount = new JPAQueryFactory(entityManager)
                .select(countryStats.count())
                .from(countryStats)
                .join(countryStats.country, country)
                .join(country.region, region)
                .where(predicate)
                .fetchOne();

        return new CountryYearStatsSearchResponsePage(results, totalAmount);
    }
}
