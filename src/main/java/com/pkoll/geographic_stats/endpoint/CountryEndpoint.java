package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.dto.CountrySummaryDTO;
import com.pkoll.geographic_stats.dto.CountryYearStatsSearchResponseDTO;
import com.pkoll.geographic_stats.dto.CountyYearStatsSearchRequestDTO;
import com.pkoll.geographic_stats.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "find-most-productive-years", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CountryYearStatsDTO> findMostProductiveYears() {
        return countryService.findMostProductiveYears();
    }

    @PostMapping(value = "search-year-stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryYearStatsSearchResponseDTO> searchYearStats(@RequestBody @Valid CountyYearStatsSearchRequestDTO requestDTO) {
        return countryService.searchYearStats(requestDTO);
    }
}
