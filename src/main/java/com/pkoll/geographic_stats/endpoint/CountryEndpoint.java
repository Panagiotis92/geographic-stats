package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.persistence.projection.CountrySummaryProjection;
import com.pkoll.geographic_stats.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("country")
public class CountryEndpoint {
    private CountryService countryService;

    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountrySummaryProjection> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping(value = "{countryCode2:[A-Z]{2}}/languages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCountryLanguages(@PathVariable String countryCode2) {
        return countryService.getCountryLanguages(countryCode2);
    }
}
