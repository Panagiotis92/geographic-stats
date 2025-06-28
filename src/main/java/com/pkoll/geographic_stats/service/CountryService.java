package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.CountryProductivityDTO;
import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.persistence.CountryStat;
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
    public Collection<CountryProductivityDTO> findMostProductiveYears() {
        return countryStatsRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        countryStat -> countryStat.getCountry().getCountryCode3(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(CountryStat::getGdpPopulationRation)),
                                mostProductiveYear -> new CountryProductivityDTO(mostProductiveYear.get()))))
                .values();
    }
}
