package com.krishna.TexasHamburger.service;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.OpenHour;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LocationsService {
    Locations addLocations(Locations location) throws FormatException;
    List<Locations> getLocations();
    Optional<Locations> getLocationById(Long id);
     void deleteLocation(Long id);

    Page<Locations> getLocations(Integer offset, int pageSize);
    Locations updateLocation(Locations location);

}
