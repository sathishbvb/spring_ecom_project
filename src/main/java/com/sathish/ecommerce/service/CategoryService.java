package com.sathish.ecommerce.service;

import com.sathish.ecommerce.exceptions.ResourceNotFoundException;
import com.sathish.ecommerce.model.Category;
import com.sathish.ecommerce.payload.CategoryDTO;
import com.sathish.ecommerce.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {


    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public CategoryResponse getAllCategories();

    public CategoryDTO deleteCategory(Long id) throws ResourceNotFoundException;

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ResourceNotFoundException;

}
