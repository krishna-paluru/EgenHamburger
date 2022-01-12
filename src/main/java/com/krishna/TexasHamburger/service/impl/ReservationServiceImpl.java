package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.Reservation;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.ReservationRepository;
import com.krishna.TexasHamburger.service.LocationsService;
import com.krishna.TexasHamburger.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private LocationsRepository locationsRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private LocationsService locationsService;
    @Override
    public void bookReservation(Reservation reservation, Long locationId) throws ResourceNotFoundException {
//       Locations location = locationsRepository.findById(locationId).get();
        Locations location = locationsService.getLocationById(locationId).get();
           if (location.getSlotsAvailable() > 0 && location != null) {
               location.setSlotsAvailable(location.getSlotsAvailable() - 1);
               reservation.assignLocation(location);
               locationsRepository.save(location);
               reservationRepository.save(reservation);
           }
           else{
               throw new ResourceNotFoundException("No Slots available with the Id "+locationId);
           }
       }


//    @Override
//    public List<Reservation> getAllReservationsByLocation(Long id) {
//      return reservationRepository.getReservationsByLocations_LocationId(id);
//    }
    public void updateReservation(Reservation reservation,Long id)
    {
        Reservation reserve = reservationRepository.findById(id).get();
        reserve.setFirstName(reservation.getFirstName());
        reserve.setLastName(reservation.getLastName());
        reserve.setDate(reservation.getDate());
        reservationRepository.save(reserve);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByLocation(Long id) {
        return reservationRepository.getReservationByLocation_LocationId(id);
    }
}
