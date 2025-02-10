package com.mahadi.InventoryManagementSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mahadi.InventoryManagementSystem.enums.TransactionStatus;
import com.mahadi.InventoryManagementSystem.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class TransactionDTO {

private Long trnsactionId;
	
	private Integer totalProducts;
	private BigDecimal totalPrice;
	private TransactionType transactionType; 
	private TransactionStatus transactionStatus; 
	private String description;
	private String note;
	
	private LocalDateTime updatedAt;
	private final LocalDateTime createdAt = LocalDateTime.now();

	private ProductDTO product;
	

	private UserDTO user;

	private SupplierDTO supplier;
}
