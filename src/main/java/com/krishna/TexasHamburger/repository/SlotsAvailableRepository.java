package com.krishna.TexasHamburger.repository;


import com.krishna.TexasHamburger.model.SlotsAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotsAvailableRepository extends JpaRepository<SlotsAvailable,Long> {
    List<SlotsAvailable> getSlotsAvailableByLocations_LocationId(Long locationId);

    @Query(value = "SELECT  a from SlotsAvailable a where a.date=?2 and a.locations.locationId = ?1")
    public SlotsAvailable getSlotsAvailable(Long id,LocalDate date);
}
