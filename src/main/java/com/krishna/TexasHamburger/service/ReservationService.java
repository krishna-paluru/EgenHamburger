package com.krishna.TexasHamburger.service;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Reservation;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    public void bookReservation(Reservation reservation, Long locationId, LocalDate date) throws ResourceNotFoundException;


//    public List<Reservation> getAllReservationsByLocation(Long id);
    public void updateReservation(Reservation reservation,Long id);

   public void deleteReservation(Long id) throws ResourceNotFoundException;

    public List<Reservation> getReservations();

    List<Reservation>getReservationsByLocation(Long id) throws ResourceNotFoundException;
}
