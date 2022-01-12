package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.Exception.RestaurantNotFoundException;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.service.LocationMenuService;
import com.krishna.TexasHamburger.service.LocationsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.xml.stream.Location;
import java.util.List;


@RestController
@RequestMapping("/TexasHamburger")
public class LocationsController {

    Logger logger= LoggerFactory.getLogger(LocationsController.class);
    private final LocationsService locationsService;
    private final LocationMenuService locationMenuService;
    LocationsController(final LocationsService locationsService,
                        final LocationMenuService locationMenuService
                        ){
        this.locationsService = locationsService;
        this.locationMenuService = locationMenuService;
    }
    @ApiOperation(value = "To get the Location by Id", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Location Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/get/{locationId}")
    @ResponseBody
    public Locations getLocationById(@PathVariable Long locationId)  {
        logger.trace("getLocationById method accessed");
        Locations locations = locationsService.getLocationById(locationId)
                .orElseThrow(() -> new RestaurantNotFoundException(locationId));

        return locations;
    }
    @ApiOperation(value = "To Add a new Location", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Location Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/add")
    public Locations addLocation(@RequestBody Locations location) throws FormatException {
        logger.trace("addLocation method accessed");
        return locationsService.addLocations(location);
    }

    @ApiOperation(value = "To get all the available locations present", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Location Not Added"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/all/{offset}")
    public Page<Locations> getLocations(@PathVariable Integer offset){
        logger.trace("getLocations method accessed");
        Page<Locations> locations =locationsService.getLocations(offset,3);
        return locations;
    }
    @ApiOperation(value = "To add MenuItems to a particular locations by locationId", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Menu Item Not Added"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/addMenuItems/{locationId}")
    public Locations addMenuItems(@PathVariable Long locationId, @RequestBody List<Long> menuItemIds) throws ResourceNotFoundException {
        logger.trace("addMenuItems method accessed");
        locationMenuService.addMenuItems(locationId, menuItemIds);
        return getLocationById(locationId);
    }

    @ApiOperation(value = "To Delete Location by Id", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Location Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id){
        logger.trace("deleteLocation method accessed");
        locationsService.deleteLocation(id);
    }

    @ApiOperation(value = "To Update Location by Id", response = Location.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Location Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("updateLocation")
    public Locations updateLocations(@RequestBody Locations location)
    {
        logger.trace("updateLocations method accessed");
        return locationsService.updateLocation(location);
    }




}
