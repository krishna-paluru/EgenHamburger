package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.MenuItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemsRepository extends JpaRepository<MenuItems,Long> {
    public List<MenuItems> getMenuItemsByCategory_CategoryName(String category);
    public Page<MenuItems> getMenuItemsByCategory_CategoryId(Long id, Pageable page);
    public List<MenuItems> getMenuItemsByCategory_CategoryId(Long id);

    public MenuItems getMenuItemsByItemId(Long id);

    @Query(value = "SELECT  a from menuItems a where a.itemId=?1")
    public  MenuItems getMenuItemById(Long id);
    MenuItems getMenuItemsByItemName(String name);


}
