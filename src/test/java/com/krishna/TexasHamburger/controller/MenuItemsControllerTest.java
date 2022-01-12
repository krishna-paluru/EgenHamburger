package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.repository.MenuItemsRepository;
import com.krishna.TexasHamburger.service.MenuItemsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class MenuItemsControllerTest {
    @Autowired
    private MenuItemsService menuItemsService;
    @MockBean
    private MenuItemsRepository menuItemsRepository;

//    @Test
//    public void addItem() throws FormatException {
//        MenuItems item = new MenuItems(2L,"cheeseBurger","2.99"
//                ,new Category(1L,"Burger"));
//        when(menuItemsRepository.save(item)).thenReturn(item);
//        assertEquals(item,menuItemsService.addMenuItems(item));
//    }

    @Test
    public void getItemByCategory()
    {
        long id=2;
        int offset = 0;
        int pageSize = 2;
        Pageable page = PageRequest.of(offset,pageSize);
        when(menuItemsRepository.getMenuItemsByCategory_CategoryId(id)).thenReturn(Stream
                .of(new MenuItems(2L,"cheeseBurger","2.99"
                        ,new Category(2L,"Burger"))).collect(Collectors.toList()));
        assertEquals(1,menuItemsService.getItemsByCategoryId(id).size());
    }

    @Test
    public  void getAllItems()
    {
        when(menuItemsRepository.findAll()).thenReturn(Stream
                .of(new MenuItems(2L,"cheeseBurger","2.99"
                                ,new Category(2L,"Burger"))
                        ,new MenuItems(3L,"ChickenBurger","2.99"
                                ,new Category(2L,"Burger"))).collect(Collectors.toList()));
        assertEquals(2,menuItemsService.getAllItems().size());
    }




}