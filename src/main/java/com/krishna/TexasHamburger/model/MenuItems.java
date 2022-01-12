package com.krishna.TexasHamburger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "menuItems")
@Data
@Table(name="menuItems")
@AllArgsConstructor
@NoArgsConstructor
public class MenuItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String itemName;
    private String price;

    @OneToOne
    @JoinColumn(name="categories",referencedColumnName = "category_id")
    private  Category category;

}
