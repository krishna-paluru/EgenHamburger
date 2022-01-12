package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.LocationMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationMenuRepository extends JpaRepository<LocationMenu,Long> {
    public void deleteLocationMenuByMenuItems_ItemId(Long id);
    public void deleteLocationMenuByLocations_LocationId(Long id);
    List<LocationMenu> getLocationMenuByLocations_LocationId(Long locationId);
}
