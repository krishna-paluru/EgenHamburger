package com.krishna.TexasHamburger.Exception;

public class ReservationException extends Exception{
    public ReservationException()
    {
        super("Error in making the Reservation");
    }
    public ReservationException(String message)
    {
        super(message);
    }

}
