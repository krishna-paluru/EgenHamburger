package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.OpenHour;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.OpenHourRepository;
import com.krishna.TexasHamburger.service.OpenHourService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;


@Service
public class OpenHourServiceImpl implements OpenHourService {

    @Autowired
    private OpenHourRepository openHourRepository;
    @Autowired
    private LocationsRepository locationsRepository;

    @Override
    public void addOpenHours(Set<OpenHour> openHour,Long locationId) {

        Locations location = locationsRepository.findById(locationId).get();
        openHour.stream().forEach(x->x.setLocations(location));
        openHourRepository.saveAll(openHour);
    }
    @Override
    public List<OpenHour> getTimingsByLocationId(Long locationId) {
        return openHourRepository.getOpenHourByLocations_LocationId(locationId);
    }


}
