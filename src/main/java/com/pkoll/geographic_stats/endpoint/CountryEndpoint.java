package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.dto.*;
import com.pkoll.geographic_stats.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("country")
@Tag(name = "Country REST Services")
public class CountryEndpoint {
    private CountryService countryService;

    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns all countries")
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

    @GetMapping(value = "most-productive-year", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finds most productive year for each country")
    @ApiResponse(responseCode = "200", description = "Success")
    public List<CountryYearStatsDTO> findMostProductiveYear() {
        return countryService.findMostProductiveYear();
    }

    @PostMapping(value = "year-stats-search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Searches for countries year stats")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Not valid criteria", content = @Content)
    public CountryYearStatsSearchResponsePage searchYearStats(@RequestBody @Valid CountyYearStatsSearchRequestDTO requestDTO) {
        return countryService.searchYearStats(requestDTO);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        ObjectError::getDefaultMessage
                ));
    }
}
