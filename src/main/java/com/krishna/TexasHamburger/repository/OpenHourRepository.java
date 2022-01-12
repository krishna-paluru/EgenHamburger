package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.OpenHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenHourRepository extends JpaRepository<OpenHour,Long> {
    List<OpenHour> getOpenHourByLocations_LocationId(Long locationId);
    void deleteOpenHourByLocations_LocationId(Long locationId);
}
