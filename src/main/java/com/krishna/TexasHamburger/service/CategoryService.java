package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceAlreadyExists;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category addCategory(Category category) throws  ResourceAlreadyExists;
    public Optional<Category> deleteCategoryById(Long id);
    public  List<Category> getAll();

    public void  updateCategory(Category category) throws FormatException;

    Page<Category> getAll(int offset, int pageSize);

    Category getCategoryById(Long categoryId) throws ResourceNotFoundException;
}
