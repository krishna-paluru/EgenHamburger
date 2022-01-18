package com.krishna.TexasHamburger.IntegrationTest;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerIntegrationTest {
    @Autowired
     private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;

    @Test
    public void getAll() throws Exception {
        when(categoryService.getAll()).thenReturn(Stream
                .of(new Category(25L,"burger"),new Category(26L,"Sandwich"))
                .collect(Collectors.toList()));
                MvcResult mvc = mockMvc.perform(get("/TexasHamburger/getAll")).andReturn();
                assertEquals(2,categoryService.getAll().size());
    }
    @Test
    public void addCategory() throws Exception {
        Category category = new Category(25L,"burger");
        when(categoryService.addCategory(category)).thenReturn(category);
        MvcResult mvc = mockMvc.perform(post("/TexasHamburger/addCategory")).andReturn();
        assertEquals(category,categoryService.addCategory(category));
    }

    @Test
    public void deleteCategory() throws Exception {
        long id=25;
        Category category = new Category(25L,"burger");
        when(categoryService.deleteCategoryById(id)).thenReturn(Optional.of(category));
        MvcResult mvc = mockMvc.perform(delete("/TexasHamburger/deleteById/")).andReturn();
        assertEquals("deleted","deleted");
    }





}
