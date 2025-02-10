package com.mahadi.InventoryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mahadi.InventoryManagementSystem.entity.Supplier;
import com.mahadi.InventoryManagementSystem.entity.Transaction;



public interface TransactionRepository extends JpaRepository<Supplier, Long>{
	
	@Query("select t from Transaction t " + 
	"where YEAR(t.createdAt) = :year and MONTH(t.createdAt) = :month")
	List<Transaction> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
