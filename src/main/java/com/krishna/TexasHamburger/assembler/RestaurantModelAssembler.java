//package com.krishna.TexasHamburger.assembler;
//
//import com.krishna.TexasHamburger.controller.LocationsController;
//import com.krishna.TexasHamburger.model.Locations;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.RepresentationModelAssembler;
//import org.springframework.stereotype.Component;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@Component
//public class RestaurantModelAssembler implements RepresentationModelAssembler<Locations, EntityModel<Locations>> {
//    @Override
//    public EntityModel<Locations> toModel(Locations locations) {
//        return EntityModel.of(locations,
//                linkTo(methodOn(LocationsController.class).getLocationById(locations.getLocationId())).withSelfRel(),
//                linkTo(methodOn(LocationsController.class).getLocations()).withRel("all"));
//    }
//}
