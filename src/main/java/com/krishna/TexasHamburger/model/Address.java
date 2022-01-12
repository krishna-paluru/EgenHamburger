package com.krishna.TexasHamburger.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    public String street;
    public String city;
    public String state;
    public String zipcode;

}
