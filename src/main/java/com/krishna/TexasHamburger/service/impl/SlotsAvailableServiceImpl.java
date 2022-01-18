package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.SlotsAvailable;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.SlotsAvailableRepository;
import com.krishna.TexasHamburger.service.SlotsAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SlotsAvailableServiceImpl implements SlotsAvailableService {
    @Autowired
    private SlotsAvailableRepository slotsAvailableRepository;
    @Autowired
    private LocationsRepository locationsRepository;
    @Override
    public Set<SlotsAvailable> assignSlots(Set<SlotsAvailable> slotsAvailable, Long locationId) {
        Optional<Locations> location = locationsRepository.findById(locationId);
        location.ifPresent(locations -> slotsAvailable.forEach(x -> {
            x.setLocations(locations);
            slotsAvailableRepository.save(x);
        }));
        return slotsAvailable;
    }

    @Override
    public List<SlotsAvailable> getSlotsByLocation(Long locationId) {
        return slotsAvailableRepository.getSlotsAvailableByLocations_LocationId(locationId);
    }
    @Override
    public void updateSlots(Long slots, Long locationId, LocalDate date) {
        SlotsAvailable slot = slotsAvailableRepository.getSlotsAvailable(locationId,date);
        slot.setSlotsAvailable(slots);
        slotsAvailableRepository.save(slot);
    }
    @Override
    public void deleteById(Long id) {
        slotsAvailableRepository.deleteById(id);
    }
}
