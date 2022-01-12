package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.model.*;
import com.krishna.TexasHamburger.repository.*;
import com.krishna.TexasHamburger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationsRepository locationsRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    LocalDate currentDate = LocalDate.now();

    @Autowired
    private TodaysOrdersRepository todaysOrdersRepository;
    @Override
    public void cancelOrder(Long id) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        for(OrderDetails orderDetail: orderDetails)
        {
            long Id = orderDetail.getOrder().getOrderId();
            if( Id == id)
            {
                System.out.println(orderDetail.getOrder().getOrderId());
                orderDetailsRepository.deleteOrderDetailsByOrder_OrderId(id);
            }
        }
        orderRepository.deleteOrderByOrderId(id);
    }
    @Override
    public Order makeOrder(Order order) {
        Locations location = locationsRepository.findById(order.getLocation().getLocationId()).get();
        order.assignLocation(location);
        User user = userRepository.findById(order.getUser().getId()).get();
        order.assignUser(user);
        Set<OrderDetails> orderDetails = order.getOrderDetails();
        if(orderDetails != null){
            orderDetails.stream().forEach(x->{
                x.setUnitPrice(Double.valueOf(x.getItem().getPrice()));
                x.setPrice(x.getQuantity() * x.getUnitPrice());
            });
            order.setTotalPrice(orderDetails.stream().mapToDouble(x->x.getPrice()).sum());
        }
        order.getOrderDetails().stream().forEach(x -> x.assignOrder(order));
        return orderRepository.save(order);
    }
    @Override
    public Optional<Order> getOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }
    @Override
    public void postOrders() {
        todaysOrdersRepository.deleteAll();
        List<Order> orders = orderRepository.findAll();
       orders.stream().forEach(x-> {
           if(x.getDate().equals(currentDate))
           {
               TodaysOrder todaysOrder = new TodaysOrder();
               todaysOrder.setLocationId(x.getLocation().getLocationId());
               todaysOrder.setUserId(x.getUser().getId());
               todaysOrder.setTotalPrice(x.getTotalPrice());
               todaysOrder.setOrderId(x.getOrderId());
               todaysOrdersRepository.save(todaysOrder);
           }

       });
//

        }
    }

