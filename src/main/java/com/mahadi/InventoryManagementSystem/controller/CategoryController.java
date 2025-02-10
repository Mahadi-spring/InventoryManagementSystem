package com.mahadi.InventoryManagementSystem.controller;

import com.mahadi.InventoryManagementSystem.dto.CategoryDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

   @PostMapping("/add")
   @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory (@RequestBody @Valid CategoryDTO categoryDTO){
       return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
   }

   @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories (){
       return ResponseEntity.ok(categoryService.getAllCategories());
   }

   @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
   }

   @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id){
       return ResponseEntity.ok(categoryService.deleteCategory(id));
   }
}


