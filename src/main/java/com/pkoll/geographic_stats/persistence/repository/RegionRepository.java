package com.pkoll.geographic_stats.persistence.repository;

import com.pkoll.geographic_stats.persistence.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query("SELECT r.name from Region r")
    public List<String> selectAll();
}
