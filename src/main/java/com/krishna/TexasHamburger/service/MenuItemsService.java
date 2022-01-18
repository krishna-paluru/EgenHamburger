package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceAlreadyExists;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.MenuItems;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuItemsService {
    public MenuItems deleteItemById(Long id) throws ResourceNotFoundException;
    public void addMenuItems(MenuItems menuItems) throws  ResourceAlreadyExists;
    public Page<MenuItems> getItemsByCategoryId(Long id, int offset, int pageSize) throws ResourceNotFoundException;
    public List<MenuItems> getItemsByCategoryId(Long id);

    public MenuItems getByMenuItemsId(Long id) ;
    public List<MenuItems>  getAllItems();

    Page<MenuItems> getAllItems(int offset,int pageSize);

    MenuItems update(MenuItems menuItems);
}
