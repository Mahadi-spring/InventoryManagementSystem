package com.mahadi.InventoryManagementSystem.service.impl;

import com.mahadi.InventoryManagementSystem.dto.CategoryDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.entity.Category;
import com.mahadi.InventoryManagementSystem.exceptions.NotFoundException;
import com.mahadi.InventoryManagementSystem.repository.CategoryRepository;
import com.mahadi.InventoryManagementSystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("Success")
                .categoryList(categoryDTOS)
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("category not found"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .message("data found")
                .category(categoryDTO)
                .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("category not found"));
        existingCategory.setCategoryName(categoryDTO.getCategoryName());

        return Response.builder()
                .status(200)
                .message("category updated")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {

        categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("category not found"));
        categoryRepository.deleteById(id);

         return Response.builder()
                .status(200)
                .message("category deleted")
                .build();
    }
}
