package com.krishna.TexasHamburger.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Data
@Entity
@Component
@Table(name="todaysOrders")
public class TodaysOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalPrice;
    private Long locationId;
    private Long userId;
    private Long orderId;

}
