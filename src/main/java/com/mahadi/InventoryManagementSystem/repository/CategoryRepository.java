package com.mahadi.InventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahadi.InventoryManagementSystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
