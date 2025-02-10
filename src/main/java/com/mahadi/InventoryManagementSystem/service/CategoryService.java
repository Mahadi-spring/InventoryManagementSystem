package com.mahadi.InventoryManagementSystem.service;

import com.mahadi.InventoryManagementSystem.dto.CategoryDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;

public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);
    Response getAllCategories( );
    Response getCategoryById(Long id);
    Response updateCategory(CategoryDTO categoryDTO);
    Response createCategory(Long id, CategoryDTO categoryDTO);
    Response deleteCategory(Long id);


}
