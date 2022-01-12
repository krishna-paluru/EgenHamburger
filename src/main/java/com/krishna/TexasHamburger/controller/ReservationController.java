package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Reservation;
import com.krishna.TexasHamburger.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TexasHamburger")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    Logger logger= LoggerFactory.getLogger(ReservationController.class);

    @ApiOperation(value = "To Make a reservation to s particular location ", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping ("/makeReservations/{locationId}")
    public void makeReservations(@RequestBody Reservation reservation ,@PathVariable Long locationId) throws ResourceNotFoundException {
        logger.trace("makeReservations method accessed");
        reservationService.bookReservation(reservation,locationId);
    }

@ApiOperation(value = "To Update a reservation to the particular location ", response = Reservation.class)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "MenuItem Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
    @PutMapping("updateReservation/{id}")
    public void updateReservation(@RequestBody Reservation reservation,@PathVariable Long id)
    {
        logger.trace("updateReservation method accessed");
        reservationService.updateReservation(reservation,id);
    }

    @ApiOperation(value = "To delete a reservation to the particular location ", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("deleteReservation/{id}")
    public void deleteReservation(@PathVariable Long id)
    {
        logger.trace("deleteReservation method accessed");
        reservationService.deleteReservation(id);
    }

    @ApiOperation(value = "To get a reservations of all the location ", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getReservations")
    public List<Reservation> getReservations()
    {
        logger.trace("getReservations method accessed");
        return reservationService.getReservations();
    }

    @ApiOperation(value = "To get a reservations of a particular location ", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/reservation/{locationId}")
    public List<Reservation> getReservationByLocation(@PathVariable Long locationId)
    {
        logger.trace("getReservationByLocation method accessed");
        return reservationService.getReservationsByLocation(locationId);
    }

}
