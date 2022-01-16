package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.*;
import com.krishna.TexasHamburger.repository.*;
import com.krishna.TexasHamburger.service.LocationsService;
import com.krishna.TexasHamburger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationsServiceImpl implements LocationsService {
    @Autowired
    private LocationsRepository locationsRepository;
    @Autowired
    private LocationMenuRepository locationMenuRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuItemsRepository menuItemsRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OpenHourRepository openHourRepository;
    @Autowired
    private SlotsAvailableRepository slotsAvailableRepository;
    @Autowired
    private OrderService orderService;
    @Override
    public Locations addLocations(Locations location) throws FormatException {
        try {
            location.getLocationMenus().stream().forEach(x -> x.setLocations(location));
            locationsRepository.save(location);
        }
        catch(Exception e)
        {
            throw new FormatException("Location data is not valid");
        }
        return location;
    }

    @Override
    public List<Locations> getLocations() {
        List<Locations> locations= locationsRepository.findAll();
        return locations;
    }

    @Override
    public Optional<Locations> getLocationById(Long id) throws ResourceNotFoundException {
        Optional<Locations> location = Optional.ofNullable(locationsRepository.findById(id)
                .orElseThrow(() -> (new ResourceNotFoundException("No Location with Id-" + id))));
        return location;
    }

    @Override
    public void deleteLocation(Long id) {
        List<LocationMenu> locationMenus = locationMenuRepository.getLocationMenuByLocations_LocationId(id);
        locationMenus.stream().forEach(x->locationMenuRepository.deleteLocationMenuByLocations_LocationId(id));
        List<Order> orders = orderRepository.getOrdersByLocation_LocationId(id);
        orders.stream().forEach(x->orderService.cancelOrder(x.getOrderId()));
        List<Reservation> reservations = reservationRepository.getReservationByLocation_LocationId(id);
        reservations.stream().forEach(x->reservationRepository.deleteReservationByLocation_LocationId(id));
        List<OpenHour> openHours = openHourRepository.getOpenHourByLocations_LocationId(id);
        openHours.stream().forEach(x->openHourRepository.deleteOpenHourByLocations_LocationId(id));
        List<SlotsAvailable> slotsAvailable = slotsAvailableRepository.getSlotsAvailableByLocations_LocationId(id);
        slotsAvailable.stream().forEach(x->slotsAvailableRepository.deleteById(x.getSlotsId()));
        locationsRepository.deleteById(id);
    }

    @Override
    public Page<Locations> getLocations(Integer offset, int pageSize) {
        Pageable page = PageRequest.of(offset,pageSize);
        Page<Locations> locations = locationsRepository.findAll(page);
        return locations;

    }

    @Override
    public Locations updateLocation(Locations location) {
        return locationsRepository.save(location);
    }




}
