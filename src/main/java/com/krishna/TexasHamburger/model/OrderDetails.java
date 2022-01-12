package com.krishna.TexasHamburger.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderDetailId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="order_id")
    private Order order;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "menu_item_id")
    private MenuItems item;

    private Integer quantity;

    private Double price;

    private Double unitPrice;

    public void assignOrder(Order order)
    {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(orderDetailId, that.orderDetailId) &&
                Objects.equals(order, that.order) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price) &&
                Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailId, order, quantity, price, unitPrice);
    }
}