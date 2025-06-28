package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.persistence.repository.CountryLanguageRepository;
import com.pkoll.geographic_stats.persistence.repository.CountryRepository;
import com.pkoll.geographic_stats.persistence.repository.CountryStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private CountryLanguageRepository countryLanguageRepository;
    private CountryStatsRepository countryStatsRepository;

    public CountryService(CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository, CountryStatsRepository countryStatsRepository) {
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryStatsRepository = countryStatsRepository;
    }

    @Transactional
    public List<CountrySummaryDTO> getAll() {
        return countryRepository.selectSummaryOrderByNameAsc();
    }

    @Transactional
    public List<String> getLanguages(String countryCode2) {
        return countryLanguageRepository.selectLanguagesByCountryCode2(countryCode2);
    }

    @Transactional
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
}
