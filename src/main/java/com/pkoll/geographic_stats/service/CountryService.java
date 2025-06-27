package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.persistence.repository.CountryLanguageRepository;
import com.pkoll.geographic_stats.persistence.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private CountryLanguageRepository countryLanguageRepository;

    public CountryService(CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository) {
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public List<CountrySummaryDTO> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public List<String> getCountryLanguages(String countryCode2) {
        return countryLanguageRepository.findLanguagesByCountryCode2(countryCode2);
    }
}
