package com.pkoll.geographic_stats.service;

import com.pkoll.geographic_stats.persistence.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<String> getAll() {
        return regionRepository.selectAll();
    }
}
