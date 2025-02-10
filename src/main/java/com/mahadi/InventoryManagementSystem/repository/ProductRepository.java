package com.mahadi.InventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahadi.InventoryManagementSystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
