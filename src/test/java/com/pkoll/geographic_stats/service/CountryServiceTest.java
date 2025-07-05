package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.dto.CountryYearStatsDTO;
import com.pkoll.geographic_stats.persistence.repository.CountryStatsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    @Mock
    private CountryStatsRepository countryStatsRepository;
    @InjectMocks
    private CountryService countryService;

    @Test
    void findMostProductiveYear() {
        CountryYearStatsDTO greece2018Stats = new CountryYearStatsDTO("Greece", "GRC", new BigDecimal(218032000000L), 2018, 10727668);
        CountryYearStatsDTO greece2017Stats = new CountryYearStatsDTO("Greece", "GRC", new BigDecimal(203086000000L), 2017, 10754679);
        CountryYearStatsDTO france2018Stats = new CountryYearStatsDTO("France", "FRA", new BigDecimal(2777540000000L), 2018, 66987244);
        CountryYearStatsDTO france2017Stats = new CountryYearStatsDTO("France", "FRA", new BigDecimal(2586290000000L), 2017, 66865144);
        when(countryStatsRepository.selectAll()).thenReturn(List.of(greece2018Stats, greece2017Stats, france2018Stats, france2017Stats));

        Collection<CountryYearStatsDTO> mostProductiveYears = countryService.findMostProductiveYear();

        assertEquals(2, mostProductiveYears.size());
        assertTrue(mostProductiveYears.containsAll(List.of(greece2018Stats, france2018Stats)));
        verify(countryStatsRepository, times(1)).selectAll();
        verifyNoMoreInteractions(countryStatsRepository);
    }
}