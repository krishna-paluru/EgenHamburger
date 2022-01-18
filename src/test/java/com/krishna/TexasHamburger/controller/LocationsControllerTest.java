package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.service.LocationsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class LocationsControllerTest {
    @Autowired
    private LocationsService locationsService;
    @MockBean
    private LocationsRepository locationsRepository;
    @Test
    public void getLocationById() throws ResourceNotFoundException {
        long id= 2;
        when(locationsRepository.findById(id)).thenReturn(Optional
                .of(new Locations(2L,"Arlington","Summit Avenue","Arlington","Texas","76013",20
                        ,new HashSet<>(),new HashSet<>(),new HashSet<>())));
        assertEquals("Arlington",locationsService.getLocationById(id).get().getLocationName());
    }
    @Test
    public void addLocation() throws FormatException {
        Locations location = new Locations(2L,"Arlington","Summit Avenue","Arlington","Texas"
                ,"76013",20,new HashSet<>(),new HashSet<>(),new HashSet<>());
        when(locationsRepository.save(location)).thenReturn(location);
        assertEquals(location,locationsService.addLocations(location));
    }
    @Test
    public void getLocations()
    {
        when(locationsRepository.findAll())
                .thenReturn(Stream.of(new Locations(2L,"Arlington","Summit Avenue"
                                        ,"Arlington","Texas","76013",20,new HashSet<>(),new HashSet<>(),new HashSet<>())
                        ,new Locations(2L,"Arlington","Summit Avenue"
                                        ,"Arlington","Texas","76013",20,new HashSet<>(),new HashSet<>(),new HashSet<>()))
                        .collect(Collectors.toList()));
        assertEquals(2,locationsService.getLocations().size());
    }



}