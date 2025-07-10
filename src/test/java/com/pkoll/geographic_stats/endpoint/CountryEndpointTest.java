package com.pkoll.geographic_stats.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkoll.geographic_stats.dto.CountyYearStatsSearchRequestDTO;
import com.pkoll.geographic_stats.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryEndpoint.class)
class CountryEndpointTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CountryService countryService;

    @Test
    void searchYearStats() throws Exception {
        CountyYearStatsSearchRequestDTO requestDTO = new CountyYearStatsSearchRequestDTO("South Europe", 1990, 2000, 0, 2);
        Map<String, String> errorResponse = Map.of("pageNumber", "Should be positive integer", "pageSize", "Should be greater or equal to 5");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/country/year-stats-search")
                        .content(mapper.writeValueAsString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(mapper.writeValueAsString(errorResponse)));
    }
}