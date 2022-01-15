package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.FormatException;
import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TexasHamburger")
public class CategoryController {
    Logger logger= LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "To add a new category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Category Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/addCategory")
    public Category addCategories(@RequestBody Category category) throws FormatException {
        logger.trace("add Categories Method accessed");
        return categoryService.addCategory(category);
    }
    @ApiOperation(value = "To get all the Categories present ", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Category Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getAll/{offset}")
    public Page<Category> getAllCategory(@PathVariable int offset)
    {
        logger.trace("add getAllCategories Method accessed");
        return categoryService.getAll(offset,2);
    }

    @ApiOperation(value = "To Update the Category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Category Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @PutMapping("updateCategory")
    public void updateCategory(@RequestBody Category category) throws FormatException {
        logger.trace("updateCategory Method accessed");
        categoryService.updateCategory(category);
    }

    @ApiOperation(value = "To Delete the Category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Category Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Transactional
    @DeleteMapping("/deleteById/{id}")
    public String deleteCategory(@PathVariable Long id) throws ResourceNotFoundException {
        logger.trace("delete Category Method accessed");
        categoryService.deleteCategoryById(id);
        return "category deleted";
    }


}
