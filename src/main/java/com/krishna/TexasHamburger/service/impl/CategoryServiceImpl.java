package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.model.LocationMenu;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.repository.CategoryRepository;
import com.krishna.TexasHamburger.repository.LocationMenuRepository;
import com.krishna.TexasHamburger.repository.MenuItemsRepository;
import com.krishna.TexasHamburger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuItemsRepository menuItemsRepository;
    @Autowired
    private LocationMenuRepository locationMenuRepository;
    @Override
    public Category addCategory(Category category) throws FormatException {
        try{
            return categoryRepository.save(category);
        }
        catch(Exception e)
        {
            throw new FormatException("Incorrect category format");
        }

    }
    public Optional<Category> deleteCategoryById(Long id)  {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            List<MenuItems> menuItems = menuItemsRepository.getMenuItemsByCategory_CategoryId(id);
            for (MenuItems item : menuItems) {
                locationMenuRepository.deleteLocationMenuByMenuItems_ItemId(item.getItemId());
                menuItemsRepository.deleteById(item.getItemId());
            }
            categoryRepository.deleteById(id);
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @Override
    public void updateCategory(Category category) throws FormatException {
        try{
            categoryRepository.save(category);
        }
        catch(Exception e)
        {
            throw new FormatException("Category is in invalid format");
        }
    }

    @Override
    public Page<Category> getAll(int offset, int pageSize) {
        Pageable page = PageRequest.of(offset,pageSize);
        Page<Category> category = categoryRepository.findAll(page);
        return category;

    }

    @Override
    public Category getCategoryById(Long categoryId) throws ResourceNotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
        {
            return category.get();
        }
        else{
            throw new ResourceNotFoundException("No Category with Id - "+categoryId);
        }
    }
}
