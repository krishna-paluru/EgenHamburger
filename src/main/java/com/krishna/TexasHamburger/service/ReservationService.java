package com.krishna.TexasHamburger.service;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReservationService {
    public void bookReservation(Reservation reservation, Long locationId) throws ResourceNotFoundException;


//    public List<Reservation> getAllReservationsByLocation(Long id);
    public void updateReservation(Reservation reservation,Long id);

   public void deleteReservation(Long id);

    public List<Reservation> getReservations();

    List<Reservation>getReservationsByLocation(Long id);
}