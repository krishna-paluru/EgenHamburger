package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.LocationMenu;

import java.util.List;

public interface LocationMenuService {

    void addRestaurentMenu(LocationMenu restaurentMenu);
    void addMenuItems(Long locationId, List<Long> menuItemIds) throws ResourceNotFoundException;
}

