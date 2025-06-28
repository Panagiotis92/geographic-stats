package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.service.CountryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("country")
public class CountryEndpoint {
    private CountryService countryService;

    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountrySummaryDTO> getAll() {
        return countryService.getAll();
    }

    @GetMapping(value = "{countryCode2:[A-Z]{2}}/languages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getLanguages(@PathVariable String countryCode2) {
        return countryService.getLanguages(countryCode2);
    }

    @GetMapping(value = "most-productive-years", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CountryYearStatsDTO> getMostProductiveYears() {
        return countryService.findMostProductiveYears();
    }
}
