package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.OrderNotFoundException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.assembler.OrderModelAssembler;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.model.Order;
import com.krishna.TexasHamburger.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/TexasHamburger")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderModelAssembler assembler;

    Logger logger= LoggerFactory.getLogger(OrderController.class);

    @ApiOperation(value = "This Api allows User to place an order to a Location ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/order")
    public void order(@RequestBody Order order)
    {
        logger.trace("Order method accessed");
        orderService.makeOrder(order);
    }

    @ApiOperation(value = "This  allows to get the order ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{orderId}")
    @ResponseBody
    public EntityModel<Order> getOrder(@PathVariable Long orderId) {
        logger.trace("getOrder method accessed");
        Order order = orderService.getOrderByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return assembler.toModel(order);
    }

    @ApiOperation(value = "This Api allows  to submit all the orders of every location for a day ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/postOrders")
    public void postOrders()
    {
        logger.trace("postOrders method accessed");
        orderService.postOrders();
    }

    @ApiOperation(value = "This Api allows User to cancel an order to a Location ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @DeleteMapping("/cancelOrder/{id}")
    public void cancelOrder(@PathVariable Long id)
    {
        logger.trace("cancelOrder method accessed");
        orderService.cancelOrder(id);
    }

    @GetMapping("ordersByLocation/{locationId}")
    public List<Order> getOrdersByLocation(@PathVariable long locationId) throws ResourceNotFoundException {
        return orderService.getOrdersByLocation(locationId);
    }
}
