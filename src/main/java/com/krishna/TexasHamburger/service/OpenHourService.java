package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.OpenHour;

import java.util.List;
import java.util.Set;

public interface OpenHourService {
    void addOpenHours(Set<OpenHour> openHour,Long locationId) throws ResourceNotFoundException;
    List<OpenHour> getTimingsByLocationId(Long locationId) throws ResourceNotFoundException;
    String deleteOpenHour(Long id) throws ResourceNotFoundException;
    OpenHour updateOpenHour(OpenHour openHour,Long id) throws ResourceNotFoundException;
}
