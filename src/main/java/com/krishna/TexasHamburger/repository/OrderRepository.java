package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public void deleteOrdersByOrderId(Long id);
    public Order getOrderByOrderId(Long id);
    void deleteOrderByOrderId(Long id);
    List<Order> getOrdersByLocation_LocationId(Long id);
    void deleteOrderByLocation_LocationId(Long id);
}
