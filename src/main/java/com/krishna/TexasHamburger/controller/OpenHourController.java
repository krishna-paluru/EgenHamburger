package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.model.OpenHour;
import com.krishna.TexasHamburger.service.OpenHourService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
@RestController
@RequestMapping("/TexasHamburger")
public class OpenHourController {
    Logger logger= LoggerFactory.getLogger(OpenHourController.class);
    @Autowired
    private OpenHourService openHourService;

    @ApiOperation(value = "To Add Timings of location by Location ", response = OpenHour.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/addOpenHours/{locationId}")
    public void addOpenHours(@RequestBody Set<OpenHour> openHour, @PathVariable Long locationId) throws ResourceNotFoundException {
        logger.trace("addOpenHours method accessed");
        openHourService.addOpenHours(openHour,locationId);
    }

    @ApiOperation(value = "To Add Timings of location by Location ", response = OpenHour.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("getTimingsByLocation/{locationId}")
    public List<OpenHour> getTimings(@PathVariable Long locationId) throws ResourceNotFoundException {
        logger.trace("getTimings method accessed");
        return openHourService.getTimingsByLocationId(locationId);
    }

    @ApiOperation(value = "To Add Timings of location by Location ", response = OpenHour.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("deleteOpenHour/{id}")
    public  String deleteOpenHour(@PathVariable Long id) throws ResourceNotFoundException {
        logger.trace("delete OpenHours method accessed");
        return openHourService.deleteOpenHour(id);
    }

    @ApiOperation(value = "To Update Timings of location by Location ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("updateOpenHour/{id}")
    public OpenHour updateOpenHour(@RequestBody OpenHour openHour, @PathVariable Long id) throws ResourceNotFoundException {
        logger.trace("Update OpenHours method accessed");
        return openHourService.updateOpenHour(openHour,id);
    }

}
