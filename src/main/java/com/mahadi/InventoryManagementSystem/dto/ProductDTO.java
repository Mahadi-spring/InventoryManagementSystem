package com.mahadi.InventoryManagementSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

	private Long productId;
	private Long categoryId;
	private Long supplierId;
	
	private String productName;
	private String productSKU;
	private BigDecimal price;
	private Long stockQuantity;
	
	private String description;
	private String imageUrl;
	private LocalDateTime expireDate;
	private LocalDateTime updatedAt;
	private final LocalDateTime createdAt = LocalDateTime.now();
	private CategoryDTO category;

	
	
}


