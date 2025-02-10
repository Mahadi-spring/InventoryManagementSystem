package com.mahadi.InventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahadi.InventoryManagementSystem.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
