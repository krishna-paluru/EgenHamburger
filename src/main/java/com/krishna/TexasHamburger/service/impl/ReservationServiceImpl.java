package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.Reservation;
import com.krishna.TexasHamburger.model.SlotsAvailable;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.ReservationRepository;
import com.krishna.TexasHamburger.repository.SlotsAvailableRepository;
import com.krishna.TexasHamburger.service.LocationsService;
import com.krishna.TexasHamburger.service.ReservationService;
import com.krishna.TexasHamburger.service.SlotsAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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
    @Autowired
    private SlotsAvailableRepository slotsAvailableRepository;

    @Override
    public void bookReservation(Reservation reservation, Long locationId, LocalDate date) throws ResourceNotFoundException {
        SlotsAvailable slotsAvailable = slotsAvailableRepository.getSlotsAvailable(locationId,date);
        if(date.isBefore(LocalDate.now()))
        {
            throw new RuntimeException("Pick a correct date");
        }
        if(slotsAvailable.getSlotsAvailable()>0)
        {
            slotsAvailable.setSlotsAvailable(slotsAvailable.getSlotsAvailable()-1);
            reservation.assignLocation(slotsAvailable.getLocations());
            slotsAvailableRepository.save(slotsAvailable);
            reservationRepository.save(reservation);
        }
        else{
               throw new ResourceNotFoundException("No Slots available with the Id "+locationId);
        }
    }
    public void updateReservation(Reservation reservation,Long id)
    {
        Reservation reserve = reservationRepository.findById(id).get();
        reserve.setFirstName(reservation.getFirstName());
        reserve.setLastName(reservation.getLastName());
        reserve.setDate(reservation.getDate());
        reservationRepository.save(reserve);
    }

    @Override
    public void deleteReservation(Long id) throws ResourceNotFoundException {
        Optional<Reservation>  reservation = reservationRepository.findById(id);
        if(reservation.isPresent())
        {
            reservationRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No reservation with Id"+ id);

        }
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByLocation(Long id) throws ResourceNotFoundException {
        locationsService.getLocationById(id);
        return reservationRepository.getReservationByLocation_LocationId(id);
    }
}
