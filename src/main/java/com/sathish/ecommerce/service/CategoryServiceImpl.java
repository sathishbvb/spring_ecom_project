package com.sathish.ecommerce.service;

import com.sathish.ecommerce.exceptions.ResourceNotFoundException;
import com.sathish.ecommerce.model.Category;
import com.sathish.ecommerce.payload.CategoryDTO;
import com.sathish.ecommerce.payload.CategoryResponse;
import com.sathish.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category persistedCategory = categoryRepository.save(category);
        return modelMapper.map(persistedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        CategoryResponse response = new CategoryResponse();
        response.setData(categories.stream()
                .map(category -> modelMapper
                .map(category, CategoryDTO.class))
                .toList());
        return response;
    }

    @Override
    public CategoryDTO deleteCategory(Long id) throws ResourceNotFoundException {
        Optional<Category> catergoryOptional = categoryRepository.findById(id);
        Category persistedCategory = catergoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category",id));
        categoryRepository.delete(persistedCategory);
        return modelMapper.map(persistedCategory,CategoryDTO.class);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Optional<Category> catergoryOptional = categoryRepository.findById(category.getCategoryId());
        Category persistedCategory = catergoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category",category.getCategoryId()));
        persistedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(persistedCategory);
        return modelMapper.map(persistedCategory,CategoryDTO.class);

    }

}
