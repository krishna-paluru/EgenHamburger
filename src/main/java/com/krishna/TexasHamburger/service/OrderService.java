package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.model.Order;

import java.util.Optional;

public interface OrderService {
      void cancelOrder(Long id);
    Order makeOrder(Order order);
    Optional<Order> getOrderByOrderId(Long orderId);
    void postOrders();
}
