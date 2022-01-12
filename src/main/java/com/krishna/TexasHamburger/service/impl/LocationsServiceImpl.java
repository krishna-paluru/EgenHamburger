package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.model.*;
import com.krishna.TexasHamburger.repository.*;
import com.krishna.TexasHamburger.service.LocationsService;
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
    public Optional<Locations> getLocationById(Long id)  {
        Optional<Locations> location = locationsRepository.findById(id);
        return location;
    }

//    @Override
//    public String bookATable(Long locationId) {
//
//        Optional<Locations> restaurant = getLocationById(locationId);
//        if(restaurant.isPresent()){
//            int slotsAvailable = restaurant.get().getSlotsAvailable();
//            if(slotsAvailable > 0) {
//                restaurant.get().setSlotsAvailable(slotsAvailable - 1);
//                locationsRepository.save(restaurant.get());
//                return "success";
//            }else{
//                return "Restaurant fully booked. Please try for an alternate day.";
//            }
//        }else{
//            return "Failed to book a table. Please try later !!!";
//        }
//    }

    @Override
    public void deleteLocation(Long id) {
        List<LocationMenu> locationMenus = locationMenuRepository.getLocationMenuByLocations_LocationId(id);
        locationMenus.stream().forEach(x->locationMenuRepository.deleteLocationMenuByLocations_LocationId(id));
        List<Order> orders = orderRepository.getOrdersByLocation_LocationId(id);
        orders.stream().forEach(x-> {
            orderDetailsRepository.deleteOrderDetailsByOrder_OrderId(x.getOrderId());
            orderRepository.deleteOrderByLocation_LocationId(id);});
        List<Reservation> reservations = reservationRepository.getReservationByLocation_LocationId(id);
        reservations.stream().forEach(x->reservationRepository.deleteReservationByLocation_LocationId(id));
        List<OpenHour> openHours = openHourRepository.getOpenHourByLocations_LocationId(id);
        openHours.stream().forEach(x->openHourRepository.deleteOpenHourByLocations_LocationId(id));
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
