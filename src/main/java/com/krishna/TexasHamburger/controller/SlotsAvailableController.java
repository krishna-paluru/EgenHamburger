package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.model.SlotsAvailable;
import com.krishna.TexasHamburger.service.SlotsAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/TexasHamburger")
public class SlotsAvailableController {

    @Autowired
    private SlotsAvailableService slotsAvailableService;

    @PostMapping("/assignSlots/{locationId}")
    public Set<SlotsAvailable> assignSlots(@RequestBody Set<SlotsAvailable> slots, @PathVariable Long locationId)
    {
        return slotsAvailableService.assignSlots(slots,locationId);
    }
    @GetMapping("/getSlots/{locationId}")
    public List<SlotsAvailable> getSlotsByLocation(@PathVariable Long locationId)
    {
        return slotsAvailableService.getSlotsByLocation(locationId);
    }

    @PutMapping("/updateSlots/{locationId}/{date}")
    public void updateSlots(@RequestBody Long slots, @PathVariable String date,@PathVariable Long locationId)
    {
        slotsAvailableService.updateSlots(slots,locationId, LocalDate.parse(date));
    }

    @DeleteMapping("delete/{slotId}")
    public void deleteById(@PathVariable Long slotId)
    {
        slotsAvailableService.deleteById(slotId);
    }
}
