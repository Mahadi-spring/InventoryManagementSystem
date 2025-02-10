package com.mahadi.InventoryManagementSystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mahadi.InventoryManagementSystem.enums.UserRole;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(content = Include.NON_NULL)

public class Response {

	// generic
	private int status;
	private String message;
	
	// for login
	private String token;
	private String expirationTime;
	private UserRole role;
	
	// for pagination
	private Integer totalPages;
	private Long totalElement;
	
	// data output optional
	private UserDTO user;
	private List<UserDTO> userList;
	
	private SupplierDTO suppliers;
	private List<SupplierDTO> supplierList;
	
	private CategoryDTO category;
	private List<CategoryDTO> categoryList;
	
	private ProductDTO product;
	private List<ProductDTO> productList;
	
	private TransactionDTO transaction;
	private List<TransactionDTO> transactionList;
	
	private final LocalDateTime timestamp = LocalDateTime.now();



}

