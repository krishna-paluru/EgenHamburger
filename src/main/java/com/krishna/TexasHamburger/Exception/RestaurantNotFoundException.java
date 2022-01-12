package com.krishna.TexasHamburger.Exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id){
        super("Could not find restaurant with id - "+ id);
    }
}
