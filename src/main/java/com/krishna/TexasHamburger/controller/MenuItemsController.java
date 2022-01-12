package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.MenuItems;
import com.krishna.TexasHamburger.service.MenuItemsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/TexasHamburger")
public class MenuItemsController {

    Logger logger= LoggerFactory.getLogger(MenuItemsController.class);
    @Autowired
    private MenuItemsService menuItemsService;

    @ApiOperation(value = "To Add MenuItems ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @PostMapping("/addItems")
    public void addItem(@RequestBody MenuItems menuItems) throws FormatException {
        logger.trace("addItem method accessed");
        menuItemsService.addMenuItems(menuItems);
    }

    @ApiOperation(value = "To Get  MenuItems by Category ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getItemById/{id}/{offset}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id, @PathVariable int offset) throws ResourceNotFoundException {
        logger.trace("getByCategoryId method accessed");
        Page<MenuItems> menuItems = menuItemsService.getItemsByCategoryId(id,offset,2);
            if (menuItems.getSize() > 0) {
                return new ResponseEntity<>(menuItems, HttpStatus.OK);
            }
            else{
                throw new ResourceNotFoundException("Category with the id not found--"+ id);
            }
    }
    @ApiOperation(value = "To get a Particular menuItem by Id ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getByMenuItemId/{id}")
    public MenuItems getByMenuItemId(@PathVariable Long id) throws ResourceNotFoundException {
        logger.trace("getByMenuItemId method accessed");
        MenuItems items = menuItemsService.getByMenuItemsId(id);
        if(items !=null)
        {
            return items;
        }
        else{
            throw new ResourceNotFoundException("No menuItem with the Id-"+id);
        }
    }
    @ApiOperation(value = "To get all MenuItems ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getAllItems/{offset}")
    public Page<MenuItems> getAllItems(@PathVariable int offset)
    {
        logger.trace("getAllItems method accessed");
        return menuItemsService.getAllItems(offset,8);
    }

    @ApiOperation(value = "To delete MenuItems by Id ", response = MenuItems.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "MenuItem Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @DeleteMapping("/deleteItem/{id}")
    public MenuItems deleteItem(@PathVariable Long id) throws ResourceNotFoundException {
        logger.trace("deleteItem method accessed");
        return menuItemsService.deleteItemById(id);
    }

    @PutMapping("/updateMenuItem")
    public MenuItems updateMenuItem(@RequestBody MenuItems menuItems)
    {
        logger.trace("updateMenuItem method accessed");
        return menuItemsService.update(menuItems);
    }


}
