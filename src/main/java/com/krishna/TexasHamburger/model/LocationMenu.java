package com.krishna.TexasHamburger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="LocationMenu")
@Data
@Table(name="location_menu")
public class LocationMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="location_Id")
    private Locations locations;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "menu_item_id")
    private MenuItems menuItems;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Locations getLocations() {
//        return locations;
//    }
//
//    public void setLocations(Locations locations) {
//        this.locations = locations;
//    }
//
//    public MenuItems getMenuItems() {
//        return menuItems;
//    }
//
//    public void setMenuItems(MenuItems menuItems) {
//        this.menuItems = menuItems;
//    }

    @Override
    public String toString() {
        return "LocationMenu{" +
                "id=" + id +
                ", menuItems=" + menuItems +
                '}';
    }
}
