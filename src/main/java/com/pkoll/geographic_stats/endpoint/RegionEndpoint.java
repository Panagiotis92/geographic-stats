package com.pkoll.geographic_stats.endpoint;

import com.pkoll.geographic_stats.service.RegionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("region")
public class RegionEndpoint {
    private RegionService regionService;

    public RegionEndpoint(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAll() {
        return regionService.getAll();
    }
}
