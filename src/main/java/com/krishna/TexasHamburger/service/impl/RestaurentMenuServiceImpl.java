package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.LocationMenu;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.repository.LocationMenuRepository;
import com.krishna.TexasHamburger.service.LocationMenuService;
import com.krishna.TexasHamburger.service.LocationsService;
import com.krishna.TexasHamburger.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurentMenuServiceImpl implements LocationMenuService {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private MenuItemsService menuItemsService;

    @Autowired
    private LocationMenuRepository repository;

    @Override
    public void addRestaurentMenu(LocationMenu restaurentMenu) {
        repository.save(restaurentMenu);
    }

    @Override
    public void addMenuItems(Long locationId, List<Long> menuItemIds) throws ResourceNotFoundException {

        Optional<Locations> location = locationsService.getLocationById(locationId);
        try {
            if (location.isPresent()) {
                menuItemIds.stream().forEach(x -> {
                    LocationMenu locationMenu = new LocationMenu();
                    locationMenu.setLocations(location.get());
                    MenuItems menuItems = menuItemsService.getByMenuItemsId(x);
                    if (menuItems != null) {
                        locationMenu.setMenuItems(menuItems);
                    }
                    repository.save(locationMenu);
                });
            }
        }
        catch(Exception e)
            {
                throw new ResourceNotFoundException("Location Not Found");
            }
    }
}
