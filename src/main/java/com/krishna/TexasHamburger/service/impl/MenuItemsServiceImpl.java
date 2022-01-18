package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceAlreadyExists;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.LocationMenu;
import com.krishna.TexasHamburger.model.Locations;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.repository.CategoryRepository;
import com.krishna.TexasHamburger.repository.LocationMenuRepository;
import com.krishna.TexasHamburger.repository.LocationsRepository;
import com.krishna.TexasHamburger.repository.MenuItemsRepository;
import com.krishna.TexasHamburger.service.CategoryService;
import com.krishna.TexasHamburger.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private LocationMenuRepository locationMenuRepository;

    @Override
    public MenuItems deleteItemById(Long id) throws ResourceNotFoundException {
                Optional<MenuItems> menuItem = menuItemsRepository.findById(id);
        if(menuItem.isPresent()) {
            List<LocationMenu> locationMenus = locationMenuRepository.findAll();
            for (LocationMenu locationMenu : locationMenus) {
                if (locationMenu.getMenuItems().getItemId()==id) {
                    locationMenuRepository.deleteLocationMenuByMenuItems_ItemId(id);
                }
            }
            menuItemsRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Item not found with Id - "+id);
        }
        return menuItem.get();
    }

    @Override
    public void addMenuItems(MenuItems menuItems) throws ResourceAlreadyExists {
        MenuItems menuItems1 = menuItemsRepository.getMenuItemsByItemName(menuItems.getItemName());
        if(menuItems1 == null)
        {
            menuItemsRepository.save(menuItems);
        }
        else{
            throw new ResourceAlreadyExists("Menu Item Already Exists");
        }
    }
    @Override
    public Page<MenuItems> getItemsByCategoryId(Long id,int offset, int pageSize) throws ResourceNotFoundException {
        Pageable page = PageRequest.of(offset,pageSize);
        categoryService.getCategoryById(id);
        return menuItemsRepository.getMenuItemsByCategory_CategoryId(id,page);
    }
    @Override
    public List<MenuItems> getItemsByCategoryId(Long id) {
        List<MenuItems> menuItems = menuItemsRepository.getMenuItemsByCategory_CategoryId(id);
        return menuItems;
    }

    @Override
    public MenuItems getByMenuItemsId(Long id)  {
        return menuItemsRepository.getMenuItemById(id);
    }

    @Override
    public List<MenuItems> getAllItems() {
        return menuItemsRepository.findAll();
    }

    @Override
    public Page<MenuItems> getAllItems(int offset, int pageSize) {
        Pageable page = PageRequest.of(offset,pageSize);
        Page<MenuItems> menuItems = menuItemsRepository.findAll(page);
        return menuItems;


    }

    @Override
    public MenuItems update(MenuItems menuItems) {
        return menuItemsRepository.save(menuItems);
    }
}
