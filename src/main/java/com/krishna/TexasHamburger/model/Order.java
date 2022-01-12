package com.krishna.TexasHamburger.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Primary;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
@Primary
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Locations location;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double totalPrice;
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Exclude
    Set<OrderDetails> orderDetails = new HashSet<>();

    public void assignUser(User user)
    {
        this.user = user;
    }
    public void assignLocation(Locations location)
    {
        this.location = location;
    }

    public void assignOrderDetails(Set<OrderDetails> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(location, order.location) &&
                Objects.equals(user, order.user) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(orderDetails, order.orderDetails);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", location=" + location +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                ", orderDetails=" + orderDetails +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, user, totalPrice);
    }
}
