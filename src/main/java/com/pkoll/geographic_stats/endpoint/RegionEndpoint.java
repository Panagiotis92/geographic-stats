package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("region")
@Tag(name = "Region REST Services")
public class RegionEndpoint {
    private RegionService regionService;

    public RegionEndpoint(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns all regions ordered by name ascending")
    @ApiResponse(responseCode = "200", description = "Success")
    public List<String> getAll() {
        return regionService.getAll();
    }
}
