package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
    void deleteOrderDetailsByOrder_OrderId(Long id);
}
