package com.sathish.ecommerce.controller;

import com.sathish.ecommerce.exceptions.ResourceNotFoundException;
import com.sathish.ecommerce.model.Category;
import com.sathish.ecommerce.payload.CategoryDTO;
import com.sathish.ecommerce.payload.CategoryResponse;
import com.sathish.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/ecom/public/categories")
    public ResponseEntity<CategoryResponse> gerAllCategories(
            @RequestParam(name = "page") Integer pageNumber,
            @RequestParam(name = "limit") Integer limit) {
        CategoryResponse allCategories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(allCategories);
    }

    @PostMapping("/ecom/admin/add-category")
    public ResponseEntity<CategoryDTO> addNewCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO addedCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
    }

    @DeleteMapping("/ecom/admin/delete-categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId) throws ResourceNotFoundException {
        CategoryDTO deleteCategoryDTO = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(200).body(deleteCategoryDTO);
    }

    @PutMapping("/ecom/admin/update-categories")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO category) throws ResourceNotFoundException {
        CategoryDTO updateCategory = categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateCategory);
    }

}
