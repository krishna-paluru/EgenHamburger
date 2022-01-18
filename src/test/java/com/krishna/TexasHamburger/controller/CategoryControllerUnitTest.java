package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceAlreadyExists;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.repository.CategoryRepository;
import com.krishna.TexasHamburger.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryControllerUnitTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void getCategoryTest()
    {
        when(categoryRepository.findAll()).thenReturn(Stream.of(new
        Category(25L,"Sandwich"),new Category(35L,"Icecream")).collect(Collectors.toList()));
        assertEquals(2,categoryService.getAll().size());
    }
    @Test
    public void findCategoryById()
    {
        long id = 25;
        when(categoryRepository.findById(id)).thenReturn(Optional.of(new Category(55L, "burger")));
        assertEquals("burger",categoryRepository.findById(id).get().getCategoryName());
    }

    @Test
    public void addCategory() throws FormatException, ResourceAlreadyExists {
        Category category = new Category(25L,"Burger");
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category,categoryService.addCategory(category));
    }


}