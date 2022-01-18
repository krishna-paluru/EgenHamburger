package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.OpenHour;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.OpenHourRepository;
import com.krishna.TexasHamburger.service.LocationsService;
import com.krishna.TexasHamburger.service.OpenHourService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class OpenHourServiceImpl implements OpenHourService {

    @Autowired
    private OpenHourRepository openHourRepository;
    @Autowired
    private LocationsService locationsService;

    @Override
    public void addOpenHours(Set<OpenHour> openHour,Long locationId) throws ResourceNotFoundException {

        Locations location = locationsService.getLocationById(locationId).get();
        openHour.forEach(x->x.setLocations(location));
        openHourRepository.saveAll(openHour);
    }
    @Override
    public List<OpenHour> getTimingsByLocationId(Long locationId) throws ResourceNotFoundException {
        Locations location = locationsService.getLocationById(locationId).get();
        return openHourRepository.getOpenHourByLocations_LocationId(locationId);
    }

    @Override
    public String deleteOpenHour(Long id) throws ResourceNotFoundException {
        Optional<OpenHour> openHour = openHourRepository.findById(id);
        if(openHour.isPresent()){
            openHourRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No Open Hour With Id-" + id);
        }
         return "Timings deleted!!";

    }

    @Override
    public OpenHour updateOpenHour(OpenHour openHour,Long id) throws ResourceNotFoundException {

        Locations location = locationsService.getLocationById(id).get();
        openHour.setLocations(location);
        openHourRepository.save(openHour);
        return openHour;
    }


}
