package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.dto.*;
import com.pkoll.geographic_stats.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("country")
@Tag(name = "Country REST Services")
public class CountryEndpoint {
    private CountryService countryService;

    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns all countries ordered by name ascending")
    @ApiResponse(responseCode = "200", description = "Success")
    public List<CountrySummaryDTO> getAll() {
        return countryService.getAll();
    }

    @GetMapping(value = "{countryCode2:[A-Z]{2}}/languages", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns all languages of country")
    @ApiResponse(responseCode = "200", description = "Success")
    public List<String> getLanguages(@PathVariable String countryCode2) {
        return countryService.getLanguages(countryCode2);
    }

    @GetMapping(value = "find-most-productive-years", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finds most productive years for each country")
    @ApiResponse(responseCode = "200", description = "Success")
    public Collection<CountryYearStatsDTO> findMostProductiveYears() {
        return countryService.findMostProductiveYears();
    }

    @PostMapping(value = "search-year-stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Searches for countries year stats")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Not valid criteria", content = @Content)
    public CountryYearStatsSearchResponsePage searchYearStats(@RequestBody @Valid CountyYearStatsSearchRequestDTO requestDTO) {
        return countryService.searchYearStats(requestDTO);
    }
}
