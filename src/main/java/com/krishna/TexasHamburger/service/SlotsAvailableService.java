package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.model.SlotsAvailable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SlotsAvailableService {
    Set<SlotsAvailable> assignSlots(Set<SlotsAvailable> slotsAvailable,Long locationId);

    List<SlotsAvailable> getSlotsByLocation(Long locationId);

    void updateSlots(Long slots, Long locationId, LocalDate date);

    void deleteById(Long id);
}
