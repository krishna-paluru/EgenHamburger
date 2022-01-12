package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.model.OpenHour;

import java.util.List;
import java.util.Set;

public interface OpenHourService {
    void addOpenHours(Set<OpenHour> openHour,Long locationId);

    List<OpenHour> getTimingsByLocationId(Long locationId);
}
