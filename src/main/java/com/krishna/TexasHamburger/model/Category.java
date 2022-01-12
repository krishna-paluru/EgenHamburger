package com.krishna.TexasHamburger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="category_name")
    private String categoryName;

    public Category(Long i, String category) {
        this.categoryId = i;
        this.categoryName = category;
    }





   /* @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<MenuItems> menuItems = new HashSet<>();*/
}
